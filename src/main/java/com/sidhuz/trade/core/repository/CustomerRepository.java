package com.sidhuz.trade.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    private static final String CUSTOMER_QUERY = " " +
            " select customer_id " +
            "   from customer " +
            "  where customer_id = :customerId ";

    public boolean isValid(int customerId){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("customerId", customerId);
        try{
            npjt.queryForObject(CUSTOMER_QUERY, params, Integer.class);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }

}
