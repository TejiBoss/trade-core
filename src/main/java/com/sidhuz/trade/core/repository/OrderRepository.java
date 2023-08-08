package com.sidhuz.trade.core.repository;

import com.sidhuz.trade.core.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import java.util.List;

import static com.sidhuz.trade.core.utils.Constants.SUBMITTED;

@Repository
public class OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    private static final String INSERT_ORDER = " " +
            " insert into customer_order ( " +
            "    customer_id, order_type, ticker_id, order_price, order_qty, " +
            "    order_ts, order_status, action_ts " +
            "  ) values ( " +
            "    :customerId, :orderType, :tickerId, :orderPrice, :orderQty, " +
            "    :orderTs, :orderStatus, :actionTs " +
            "  ) ";

    private static final String GET_ORDER = " " +
            " select order_id, ticker_id, customer_id, order_type, order_price, order_qty, order_ts, order_status " +
            "   from customer_order " +
            "  where order_id = :orderId ";

    private static final String UPDATE_ORDER_STATUS = " " +
            " update customer_order " +
            "    set order_status = :orderStatus, " +
            "        action_ts = :actionTs " +
            "  where order_id = :orderId";

    public int insert(Order order) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("customerId", order.getCustomerId());
        params.addValue("orderType", order.getOrderType());
        params.addValue("tickerId", order.getTickerId());
        params.addValue("orderPrice", order.getOrderPrice());
        params.addValue("orderQty", order.getOrderQty());
        params.addValue("orderTs", order.getOrderTs(), JDBCType.TIMESTAMP.getVendorTypeNumber());
        params.addValue("orderStatus", SUBMITTED);
        params.addValue("actionTs", LocalDateTime.now(), JDBCType.TIMESTAMP.getVendorTypeNumber());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        npjt.update(INSERT_ORDER, params, keyHolder);
        return (int) keyHolder.getKeys().get("order_id");
    }

    public Order get(int orderId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", orderId);
        List<Order> orderList = npjt.query(GET_ORDER, params, new BeanPropertyRowMapper<>(Order.class));
        if (CollectionUtils.isEmpty(orderList)) {
            return null;
        }
        return orderList.get(0);
    }

    public void updateStatus(int orderId, String orderStatus) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("orderId", orderId);
        params.addValue("orderStatus", orderStatus);
        params.addValue("actionTs", LocalDateTime.now(), JDBCType.TIMESTAMP.getVendorTypeNumber());
        npjt.update(UPDATE_ORDER_STATUS, params);
    }
}
