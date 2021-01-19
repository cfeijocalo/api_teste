package com.me.teste.api_teste.controller;

import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.response.PedidoResponse;
import com.me.teste.api_teste.service.PedidoService;

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
    PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> getPedido(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> createPedido(@RequestBody PedidoPayload pedido) {
        return new ResponseEntity<>(service.create(pedido), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PedidoResponse> updatePedido(@RequestBody PedidoPayload pedido) {
        return new ResponseEntity<>(service.update(pedido), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePedido(@PathVariable("id") String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
