package com.sidhuz.trade.core.repository;

import com.sidhuz.trade.core.model.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class TickerRepository {

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    private static final String ALL_TICKER_QUERY = " " +
            " select ticker_id, ticker_name, last_ticker_price, action_ts " +
            "   from ticker ";
    public List<Ticker> getAll() {
        return npjt.query(ALL_TICKER_QUERY, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Ticker.class));
    }

    private static final String TICKER_QUERY = " " +
            " select ticker_id, ticker_name, last_ticker_price, action_ts " +
            "   from ticker " +
            "  where ticker_id = :tickerId";
    public Ticker get (String tickerId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tickerId", tickerId);
        List<Ticker> tickerList = npjt.query(TICKER_QUERY, params, new BeanPropertyRowMapper<>(Ticker.class));
        if (CollectionUtils.isEmpty(tickerList)) {
            throw new RuntimeException("Invalid Ticker Id " + tickerId);
        }
        return tickerList.get(0);
    }

}
