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
        //TODO: Update Final Quantity on Customer Holding when a trade is Executed.
    }

    public void saveSellTrade(int customerId, String tickerId, int qty){
        //TODO: Update In-Transit Quantity and Final Quantity  on Customer Holding when a Sell Trade is Completed.
    }
}
