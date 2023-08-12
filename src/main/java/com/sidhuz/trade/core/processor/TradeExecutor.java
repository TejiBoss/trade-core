package com.sidhuz.trade.core.processor;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.model.Trade;
import com.sidhuz.trade.core.service.HoldingService;
import com.sidhuz.trade.core.service.OrderService;
import com.sidhuz.trade.core.service.TradeService;
import com.sidhuz.trade.core.utils.Constants;
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
    public void execute(Order buyOrder) {
        lockBuyOrder(buyOrder.getOrderId());
        Order sellOrder = orderService.findMatchingSellOrder(buyOrder);
        if (Objects.isNull(sellOrder)) {
            throw new RuntimeException("Unable to find a matching sell order for buy order " + buyOrder.getOrderId());
        }
        Trade trade = new Trade(buyOrder, sellOrder);
        tradeService.save(trade);
        orderService.updateStatus(buyOrder.getOrderId(), Constants.EXECUTED);
        orderService.updateStatus(sellOrder.getOrderId(), Constants.EXECUTED);
        holdingService.saveBuyTrade(trade.getBuyCustomerId(), trade.getTickerId(), trade.getTradeQty());
        holdingService.saveSellTrade(trade.getSellCustomerId(), trade.getTickerId(), trade.getTradeQty());
    }


    private void lockBuyOrder(int buyOrderId) {
        if (!orderService.lock(buyOrderId)) {
            throw new RuntimeException("Unable to lock buy order id for processing " + buyOrderId);
        }
    }
}
