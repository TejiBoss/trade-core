package com.sidhuz.trade.core.controller;

import com.sidhuz.trade.core.model.Order;
import com.sidhuz.trade.core.processor.OrderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderProcessor orderProcessor;

    @PostMapping(path = "/submit", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submit(@RequestBody Order order) {
        orderProcessor.submit(order);
        return new ResponseEntity<>("SUCCESS " + order.getOrderId() + " submitted.", HttpStatus.OK);
    }

    @PostMapping(path = "/cancel", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cancel(@RequestBody Order order){
        return new ResponseEntity<>("SUCCESS " + order.getOrderId() + " canceled.", HttpStatus.OK);
    }

}
