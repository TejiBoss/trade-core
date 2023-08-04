package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveNew(Order order){
        int orderId = orderRepository.insert(order);
        order.setOrderId(orderId);
    }

}
