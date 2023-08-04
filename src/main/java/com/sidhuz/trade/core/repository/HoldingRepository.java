package com.sidhuz.trade.core.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HoldingRepository {

    public void save (int customerId, String tickerId, int adjustQty, int adjustInTransitQty) {

    }

    public int getQty (int customerId, String tickerId) {
        return 500;
    }
}
