package com.me.teste.api_teste.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.me.teste.api_teste.model.payload.ItemPayload;
import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.response.PedidoResponse;
import com.me.teste.api_teste.model.table.Items;
import com.me.teste.api_teste.model.table.OrderItems;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.repository.ItemsRepository;
import com.me.teste.api_teste.repository.OrdersRepository;
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

    public PedidoResponse find(String id) {
        if (id != null) {
            Optional<Orders> order = ordersRepository.findById(id);
            PedidoResponse response = null;
            if (order.isPresent()) {
                List<ItemPayload> items = new ArrayList<>();
                order.get().getItems().forEach(el -> {
                    items.add(ItemPayload.builder().descricao(el.getItem().getDescription())
                            .precoUnitario(el.getUnitPrice()).qtd(el.getQuantity()).build());
                });
                response = PedidoResponse.builder().pedido(order.get().getId()).itens(items)
                        .status(Arrays.asList(order.get().getStatus())).build();
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
                Orders order = Orders.builder().id(pedido.getPedido()).status("CREATED").build();
                List<Items> items = new ArrayList<>();
                List<OrderItems> orderItems = new ArrayList<>();
                pedido.getItens().forEach(el -> {
                    Items item = Items.builder().description(el.getDescricao()).build();
                    OrderItems orderItem = OrderItems.builder().unitPrice(el.getPrecoUnitario()).quantity(el.getQtd())
                            .item(item).order(order).build();
                    orderItems.add(orderItem);
                    items.add(item);
                });
                order.setItems(orderItems);
                ordersRepository.save(order);
            }
        }
        return null;
    }

}
