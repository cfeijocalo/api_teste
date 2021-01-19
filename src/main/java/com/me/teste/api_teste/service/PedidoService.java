package com.me.teste.api_teste.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.me.teste.api_teste.model.payload.ItemPayload;
import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.response.PedidoResponse;
import com.me.teste.api_teste.model.table.OrderItems;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.repository.ItemsRepository;
import com.me.teste.api_teste.repository.OrdersRepository;
import com.me.teste.api_teste.util.ModelConverter;
import com.me.teste.api_teste.util.StatusEnum;
import com.me.teste.api_teste.validator.OrderNumberValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ItemsRepository itemsRepository;

    private List<String> validate(PedidoPayload pedido) {
        OrderNumberValidator validator = new OrderNumberValidator();
        List<StatusEnum> status = validator.check(pedido, null, new ArrayList<>());
        if (!status.isEmpty()) {
            List<String> statusMessage = new ArrayList<>();
            status.forEach(element -> statusMessage.add(element.getMessage()));
            return statusMessage;
        }
        return new ArrayList<>();
    }

    private Orders findOrder(String id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            return null;
        }
    }

    public PedidoResponse find(String id) {
        if (id != null) {
            Orders orders = findOrder(id);
            PedidoResponse response = null;
            if (orders != null) {
                List<ItemPayload> items = new ArrayList<>();
                orders.getItems().forEach(el -> items.add(ItemPayload.builder().descricao(el.getItem().getDescription())
                        .precoUnitario(el.getUnitPrice()).qtd(el.getQuantity()).build()));
                response = PedidoResponse.builder().pedido(orders.getId()).itens(items)
                        .status(Arrays.asList(orders.getStatus())).build();
            }
            return response;
        }
        return null;
    }

    public PedidoResponse create(PedidoPayload pedido) {
        if (pedido != null) {
            List<String> status = validate(pedido);
            if (!status.isEmpty()) {
                return new PedidoResponse(pedido.getPedido(), pedido.getItens(), status);
            } else {
                Orders order = ModelConverter.convertsPayloadToTable(pedido);
                order.setStatus("CREATED");
                ordersRepository.save(order);
            }
        }
        return null;
    }

    public PedidoResponse update(PedidoPayload pedido) {
        if (pedido != null) {
            List<String> status = validate(pedido);
            if (!status.isEmpty()) {
                return new PedidoResponse(pedido.getPedido(), pedido.getItens(), status);
            } else {
                Orders orders = findOrder(pedido.getPedido());
                if (orders != null) {
                    Orders ordersAux = ModelConverter.convertsPayloadToTable(pedido);
                    // if (orders.getItems().size() == ordersAux.getItems().size()) {

                    // } else if (orders.getItems().size() < ordersAux.getItems().size()) {

                    // } else {

                    // }
                    List<OrderItems> orderItems = orders.getItems();

                    orders.setItems(orderItems);
                    ordersRepository.save(orders);
                }
            }
        }
        return null;
    }

    public PedidoResponse delete(String id) {
        Orders orders = findOrder(id);
        if (orders != null) {
            ordersRepository.delete(orders);
        }
        return null;
    }

}
