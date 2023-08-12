package com.sidhuz.trade.core.processor;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.model.Trade;
import com.sidhuz.trade.core.service.HoldingService;
import com.sidhuz.trade.core.service.OrderService;
import com.sidhuz.trade.core.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
public class TradeExecutor {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private HoldingService holdingService;

    @Transactional
    public void execute (Order buyOrder) {
        if (!orderService.lock(buyOrder.getOrderId())) {
            throw new RuntimeException("Unable to lock buy order id for processing " + buyOrder.getOrderId());
        };

        Order sellOrder = orderService.findSellOrder(buyOrder);
        if (Objects.nonNull(sellOrder) && orderService.lock(sellOrder.getOrderId())) {
            Trade trade = new Trade(buyOrder, sellOrder);
            tradeService.save(trade);
            holdingService.saveBuyTrade(trade.getBuyCustomerId(), trade.getTickerId(), trade.getTradeQty());
            holdingService.saveSellTrade(trade.getSellCustomerId(), trade.getTickerId(), trade.getTradeQty());
        } else {
            throw new RuntimeException("Unable to find a matching sell order for buy order " + buyOrder.getOrderId());
        }

    }
}
