package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.repository.HoldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoldingService {

    @Autowired
    private HoldingRepository holdingRepository;

    public int getHoldingQty(int customerId, String tickerId){
        return holdingRepository.getQty(customerId, tickerId);
    }

    public void saveSellOrder(int customerId, String tickerId, int qty){
        holdingRepository.save(customerId, tickerId, -1*qty, qty);
    }

    public void saveBuyTrade(int customerId, String tickerId, int qty){
        //TODO: Update Final Quantity on Customer Holding when a trade is Executed.
    }

    public void saveSellTrade(int customerId, String tickerId, int qty){
        //TODO: Update In-Transit Quantity and Final Quantity  on Customer Holding when a Sell Trade is Completed.
    }
}
