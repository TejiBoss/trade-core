package com.sidhuz.trade.core.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ticker {
    private String tickerId;
    private String tickerName;
    private LocalDateTime actionTs;
}
