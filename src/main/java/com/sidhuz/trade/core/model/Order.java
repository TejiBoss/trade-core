package com.sidhuz.trade.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private static final String BUY_ORDER_TYPE = "BUY";
    private static final String SELL_ORDER_TYPE = "SELL";
    private static final List<String> ALL_ORDER_TYPES = Arrays.asList(BUY_ORDER_TYPE, SELL_ORDER_TYPE);

    private int orderId;
    private int customerId;
    private String orderType;
    private String tickerId;
    private int orderQty;
    private double orderPrice;
    private String orderStatus;
    private LocalDateTime orderTs;

    public boolean isValidOrderType(){
        return ALL_ORDER_TYPES.contains(this.orderType.toUpperCase());
    }

    public boolean isValidOrderQty(){
        return (this.orderQty > 0);
    }

    public boolean isBuyOrder() {
        return BUY_ORDER_TYPE.equalsIgnoreCase(this.orderType);
    }

    public boolean isSellOrder() {
        return SELL_ORDER_TYPE.equalsIgnoreCase(this.orderType);
    }
}
