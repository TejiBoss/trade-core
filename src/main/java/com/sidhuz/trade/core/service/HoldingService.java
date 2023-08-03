package com.sidhuz.trade.core.service;

import org.springframework.stereotype.Service;

@Service
public class HoldingService {

    public int getHoldingQty(int customerId, String tickerId){
        return 500;
    }

    public void saveSellOrder(int customerId, String tickerId, int qty){
        //TODO: Update In-Transit Quantity on Customer Holding when a Sell Order is Placed.
    }

    public void saveBuyTrade(int customerId, String tickerId, int qty){
        //TODO: Update Final Quantity on Customer Holding when a trade is Executed.
    }

    public void saveSellTrade(int customerId, String tickerId, int qty){
        //TODO: Update In-Transit Quantity and Final Quantity  on Customer Holding when a Sell Trade is Completed.
    }
}
