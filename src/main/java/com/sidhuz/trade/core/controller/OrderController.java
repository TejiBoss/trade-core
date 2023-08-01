package com.sidhuz.trade.core.controller;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private TickerService tickerService;

    @PostMapping(path = "/process", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrder(@RequestBody Order order){
        System.out.println(order.toString());
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping(path = "/test", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testOrder(){
        System.out.println(tickerService.get("GPS"));
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
