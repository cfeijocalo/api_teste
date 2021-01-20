package com.me.teste.api_teste.controller;

import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.response.IResponse;
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
    public ResponseEntity<IResponse> getPedido(@PathVariable("id") String id) {
        return service.find(id);
    }

    @PostMapping
    public ResponseEntity<IResponse> createPedido(@RequestBody PedidoPayload pedido) {
        return service.create(pedido);
    }

    @PutMapping()
    public ResponseEntity<IResponse> updatePedido(@RequestBody PedidoPayload pedido) {
        return service.update(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePedido(@PathVariable("id") String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
