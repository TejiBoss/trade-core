package com.sidhuz.trade.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String customerId;
    private String orderType;
    private String stockSymbol;
    private int orderQuantity;
    private double orderPrice;
    private String orderStatus;
    private LocalDateTime orderDate;
}
