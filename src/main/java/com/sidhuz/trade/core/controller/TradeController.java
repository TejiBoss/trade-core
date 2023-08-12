package com.sidhuz.trade.core.controller;

import com.sidhuz.trade.core.processor.TradeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeProcessor tradeProcessor;

    @PostMapping(path = "/run")
    public ResponseEntity<?> run() {
        tradeProcessor.run();
        return new ResponseEntity<>("SUCCESS trade execution successful", HttpStatus.OK);

    }
}
