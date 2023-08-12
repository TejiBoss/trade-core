package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.repository.HoldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoldingService {

    @Autowired
    private HoldingRepository holdingRepository;

    public int getOpenQty(int customerId, String tickerId){
        return holdingRepository.getOpenQty(customerId, tickerId);
    }

    public void saveSellOrder(int customerId, String tickerId, int qty){
        holdingRepository.save(customerId, tickerId, 0, qty);
    }

    public void saveBuyTrade(int customerId, String tickerId, int qty){
        holdingRepository.save(customerId, tickerId, qty, 0);
    }

    public void saveSellTrade(int customerId, String tickerId, int qty){
        holdingRepository.save(customerId, tickerId, (qty * -1), (qty * -1));
    }
}
