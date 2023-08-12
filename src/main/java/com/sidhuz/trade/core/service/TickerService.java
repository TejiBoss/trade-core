package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TickerService {

    @Autowired
    private TickerRepository tickerRepository;

    public boolean isValid(String tickerId){
        return Objects.nonNull(tickerRepository.get(tickerId));
    }

}
