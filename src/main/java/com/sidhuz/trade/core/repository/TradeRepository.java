package com.sidhuz.trade.core.repository;

import com.sidhuz.trade.core.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import java.util.Objects;

@Repository
public class TradeRepository {

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    private static final String SAVE_TRADE = " " +
            " insert into trade ( " +
            "    buy_customer_id, buy_order_id, sell_customer_id, sell_order_id, " +
            "    ticker_id, trade_price, trade_qty, trade_ts, action_ts " +
            " ) values ( " +
            "    :buyCustomerId, :buyOrderId, :sellCustomerId, :sellOrderId, " +
            "    :tickerId, :tradePrice, :tradeQty, :tradeTs, :actionTs " +
            " ) ";

    public int save(Trade trade) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("buyCustomerId", trade.getBuyCustomerId());
        params.addValue("buyOrderId", trade.getBuyOrderId());
        params.addValue("sellCustomerId", trade.getSellCustomerId());
        params.addValue("sellOrderId", trade.getSellOrderId());
        params.addValue("tickerId", trade.getTickerId());
        params.addValue("tradePrice", trade.getTradePrice());
        params.addValue("tradeQty", trade.getTradeQty());
        params.addValue("tradeTs", trade.getTradeTs(), JDBCType.TIMESTAMP.getVendorTypeNumber());
        params.addValue("actionTs", LocalDateTime.now(), JDBCType.TIMESTAMP.getVendorTypeNumber());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        npjt.update(SAVE_TRADE, params, keyHolder);
        return 1;
        //return (int) Objects.requireNonNull(keyHolder.getKeys()).get("trade_id");
    }
}
