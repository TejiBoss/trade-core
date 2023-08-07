package com.sidhuz.trade.core.processor;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.service.CustomerService;
import com.sidhuz.trade.core.service.HoldingService;
import com.sidhuz.trade.core.service.OrderService;
import com.sidhuz.trade.core.service.TickerService;
import com.sidhuz.trade.core.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderProcessor {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HoldingService holdingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TickerService tickerService;

    @Transactional
    public void submit(Order order){
        if(validateOrder(order)) {
            order.setOrderStatus(Constants.SUBMITTED);
            saveOrder(order);
        }
    }

    private boolean validateOrder(Order order){
        validateCustomerId(order.getCustomerId());
        validateTickerId(order.getTickerId());
        validateQty(order);
        return true;
    }

    private void saveOrder(Order order){
        orderService.saveNew(order);
        if (order.isSellOrder()) {
            holdingService.saveSellOrder(order.getCustomerId(), order.getTickerId(), order.getOrderQty());
        }
    }

    private void validateQty (Order order) {
        if (order.getOrderQty() <= 0) {
            throw new RuntimeException("Invalid Qty for an order " + order.getOrderQty());
        }

        if (order.isSellOrder()) {
            int openQty = holdingService.getOpenQty(order.getCustomerId(), order.getTickerId());
            if (openQty < order.getOrderQty()) {
                throw new RuntimeException("Insufficient Qty in the account to place an order for qty " + order.getOrderQty());
            }
        }
    }

    private void validateTickerId (String tickerId) {
        if (!tickerService.isValid(tickerId)) {
            throw new RuntimeException("Invalid ticker id on the order " + tickerId);
        }
    }

    private void validateCustomerId (int customerId) {
        if (!customerService.isValid(customerId)){
            throw new RuntimeException("Invalid customer id on the order " + customerId);
        }
    }
}
