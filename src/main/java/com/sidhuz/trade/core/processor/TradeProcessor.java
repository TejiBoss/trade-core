package com.sidhuz.trade.core.processor;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TradeProcessor {

    @Autowired
    private TradeExecutor tradeExecutor;

    @Autowired
    private OrderService orderService;

    public void run() {
        List<Order> openBuyOrders = orderService.getOpenBuyOrders();
        for (Order buyOrder : openBuyOrders) {
            execute(buyOrder);
        }
    }

    private void execute (Order buyOrder) {
        try {
            System.out.println("Order: " + buyOrder.toString());
            tradeExecutor.execute(buyOrder);
        } catch (Exception e) {
            log.error("Error during execution of trade for buy order id " + buyOrder.getOrderId(), e);
        }
    }
}
