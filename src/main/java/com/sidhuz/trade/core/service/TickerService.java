package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.model.Ticker;
import com.sidhuz.trade.core.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TickerService {

    @Autowired
    private TickerRepository tickerRepository;

    public List<Ticker> getAll () {
        return tickerRepository.getAll();
    }

    public Ticker get(String tickerId) {
        return tickerRepository.get(tickerId);
    }

}
