package com.sidhuz.trade.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
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

    public boolean isValidOrderType() {
        return ALL_ORDER_TYPES.contains(this.orderType.toUpperCase());
    }

    public boolean isValidOrderQty() {
        return (this.orderQty > 0);
    }

    public boolean isBuyOrder() {
        return BUY_ORDER_TYPE.equalsIgnoreCase(this.orderType);
    }

    public boolean isSellOrder() {
        return SELL_ORDER_TYPE.equalsIgnoreCase(this.orderType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId
                && customerId == order.customerId
                && orderQty == order.orderQty
                && Double.compare(order.orderPrice, orderPrice) == 0
                && Objects.equals(orderType, order.orderType)
                && Objects.equals(tickerId, order.tickerId)
                && Objects.equals(orderTs, order.orderTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, orderType, tickerId, orderQty, orderPrice, orderStatus, orderTs);
    }
}
