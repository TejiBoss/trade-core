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

    @PostMapping(path = "/process", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        orderProcessor.process(order);
        return new ResponseEntity<>("SUCCESS " + order.getOrderId() + " created.", HttpStatus.OK);
    }

}
