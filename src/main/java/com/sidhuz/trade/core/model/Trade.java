package com.sidhuz.trade.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Trade {
    private int tradeId;
    private String tickerId;
    private double tradePrice;
    private LocalDateTime tradeTs;
    private int tradeQty;
    private int buyCustomerId;
    private int sellCustomerId;
    private int buyOrderId;
    private int sellOrderId;

    public Trade (Order buyOrder, Order sellOrder) {
        this.tickerId = buyOrder.getTickerId();
        this.tradePrice = buyOrder.getOrderPrice();
        this.tradeTs = LocalDateTime.now();
        this.tradeQty = buyOrder.getOrderQty();
        this.buyCustomerId = buyOrder.getCustomerId();
        this.sellCustomerId = sellOrder.getCustomerId();
        this.buyOrderId = buyOrder.getOrderId();
        this.sellOrderId = sellOrder.getOrderId();
    }
}
