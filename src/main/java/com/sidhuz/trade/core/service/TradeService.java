package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.model.Trade;
import com.sidhuz.trade.core.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public void save (Trade trade) {

    }
}
