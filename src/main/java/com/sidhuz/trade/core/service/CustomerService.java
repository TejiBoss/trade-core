package com.sidhuz.trade.core.service;

import com.sidhuz.trade.core.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean isValid(int customerId){
        return customerRepository.isValid(customerId);
    }
}
