package com.sidhuz.trade.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Repository
public class HoldingRepository {

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    private static final String INSERT_HOLDING = " " +
            " insert into customer_holding ( " +
            "   customer_id, ticker_id, qty, in_transit_qty, action_ts " +
            " ) values ( " +
            "   :customerId, :tickerId, :adjustQty, :adjustInTransitQty, :actionTs " +
            " ) ";

    private static final String UPDATE_HOLDING = " " +
            " update customer_holding " +
            "    set qty = qty + :adjustQty, " +
            "        in_transit_qty = in_transit_qty + :adjustInTransitQty, " +
            "        action_ts = :actionTs " +
            "  where customer_id = :customerId " +
            "    and ticker_id = :tickerId ";

    private static final String GET_OPEN_QUANTITY = " " +
            " select qty - in_transit_qty as open_qty " +
            "   from customer_holding " +
            "  where customer_id = :customerId " +
            "    and ticker_id = :tickerId ";


    private void insert(int customerId, String tickerId, int adjustQty, int adjustInTransitQty) {
        npjt.update(INSERT_HOLDING, buildParams(customerId, tickerId, adjustQty, adjustInTransitQty));
    }

    private int update(int customerId, String tickerId, int adjustQty, int adjustInTransitQty) {
        return npjt.update(UPDATE_HOLDING, buildParams(customerId, tickerId, adjustQty, adjustInTransitQty));
    }

    private MapSqlParameterSource buildParams (int customerId, String tickerId, int adjustQty, int adjustInTransitQty) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("customerId", customerId);
        params.addValue("tickerId", tickerId);
        params.addValue("adjustQty", adjustQty);
        params.addValue("adjustInTransitQty", adjustInTransitQty);
        params.addValue("actionTs", LocalDateTime.now(), JDBCType.TIMESTAMP.getVendorTypeNumber());
        return params;
    }

    public void save(int customerId, String tickerId, int adjustQty, int adjustInTransitQty) {
        int rowsUpdated = update(customerId, tickerId, adjustQty, adjustInTransitQty);
        if (rowsUpdated == 0) {
            insert(customerId, tickerId, adjustQty, adjustInTransitQty);
        }
    }

    public int getOpenQty(int customerId, String tickerId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("customerId", customerId);
        params.addValue("tickerId", tickerId);
        Integer openQty;
        try {
            openQty = npjt.queryForObject(GET_OPEN_QUANTITY, params, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }

        if (Objects.nonNull(openQty)) {
            return openQty;
        } else  {
            return 0;
        }
    }
}
