package com.me.teste.api_teste.controller;

import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.response.PedidoResponse;
import com.me.teste.api_teste.repository.ItemsRepository;
import com.me.teste.api_teste.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> getPedido(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> createPedido(@RequestBody PedidoPayload pedido) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PedidoResponse> updatePedido(@RequestBody PedidoPayload pedido) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePedido(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
