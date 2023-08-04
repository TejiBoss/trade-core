package com.sidhuz.trade.core.processor;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.service.CustomerService;
import com.sidhuz.trade.core.service.HoldingService;
import com.sidhuz.trade.core.service.OrderService;
import com.sidhuz.trade.core.service.TickerService;
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
    public void process(Order order){
        if(validateOrder(order)) {
            saveOrder(order);
        }
    }

    private boolean validateOrder(Order order){
        return true;
    }

    private void saveOrder(Order order){
        orderService.saveNew(order);
        if (order.isSellOrder()) {
            holdingService.saveSellOrder(order.getCustomerId(), order.getTickerId(), order.getOrderQty());
        }
    }
}
