package com.sidhuz.trade.core.repository;

import com.sidhuz.trade.core.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.time.LocalDateTime;

import static com.sidhuz.trade.core.utils.Constants.SUBMITTED;

@Repository
public class OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    private static final String INSERT_ORDER = " " + "  insert into customer_order ( " + "    customer_id, order_type, ticker_id, order_price, order_qty, " + "    order_ts, order_status, action_ts " + "  ) values ( " + "    :customerId, :orderType, :tickerId, :orderPrice, :orderQty, " + "    :orderTs, :orderStatus, :actionTs " + "  ) ";

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
}
