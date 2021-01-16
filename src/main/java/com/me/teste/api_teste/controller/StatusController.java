package com.me.teste.api_teste.controller;

import com.me.teste.api_teste.model.payload.StatusPayload;
import com.me.teste.api_teste.model.response.StatusResponse;
import com.me.teste.api_teste.repository.ItemsRepository;
import com.me.teste.api_teste.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @PostMapping
    public ResponseEntity<StatusResponse> setStatus(@RequestBody StatusPayload status) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
