package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.repository.OrderRepository;
import com.sidhuz.trade.core.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        int rowsUpdated = orderRepository.updateStatusWithCheck(orderId, Constants.PROCESSING, Constants.SUBMITTED);
        return rowsUpdated > 0;
    }

    public List<Order> getOpenBuyOrders(){
        return orderRepository.getOpenBuyOrders();
    }

    public void updateStatus (int orderId, String status) {
        orderRepository.updateStatus(orderId, status);
    }

    public Order findMatchingSellOrder(Order buyOrder) {
        List<Order> sellOrders = orderRepository.findMatchingSellOrder(buyOrder);
        if (CollectionUtils.isEmpty(sellOrders)) {
            return null;
        }

        for (Order order : sellOrders) {
            if (lock(order.getOrderId())) {
                return order;
            }
        }
        return null;
    }

}
