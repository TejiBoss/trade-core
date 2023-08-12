package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.repository.OrderRepository;
import com.sidhuz.trade.core.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void submit(Order order) {
        int orderId = orderRepository.insert(order);
        order.setOrderId(orderId);
    }

    public boolean isValid(Order order) {
        Order dbOrder = orderRepository.get(order.getOrderId());
        if (Objects.isNull(dbOrder)) {
            return false;
        }

        if (!Constants.SUBMITTED.equalsIgnoreCase(dbOrder.getOrderStatus())) {
            throw new RuntimeException("Only submitted orders can be canceled.");
        }
        return order.equals(dbOrder);
    }

    public void cancel(int orderId) {
        orderRepository.updateStatus(orderId, Constants.CANCELED);
    }

    public boolean lock (int orderId) {
        return true;
    }

    public List<Order> getOpenBuyOrders(){
        return null;
    }

    public Order findSellOrder (Order buyOrder) {
        return null;
    }

}
