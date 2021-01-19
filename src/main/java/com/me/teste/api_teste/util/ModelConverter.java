package com.me.teste.api_teste.util;

import java.util.ArrayList;
import java.util.List;

import com.me.teste.api_teste.model.payload.ItemPayload;
import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.response.PedidoResponse;
import com.me.teste.api_teste.model.table.Items;
import com.me.teste.api_teste.model.table.OrderItems;
import com.me.teste.api_teste.model.table.Orders;

public class ModelConverter {

    private ModelConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Orders convertsPayloadToTable(PedidoPayload payload) {
        Orders orders = new Orders();
        if (payload != null) {
            List<OrderItems> items = convertsPayloadToTable(payload.getItens());
            items.forEach(el -> el.setOrder(Orders.builder().id(payload.getPedido()).items(items).build()));
            orders = Orders.builder().id(payload.getPedido()).items(items).build();
        }
        return orders;
    }

    public static List<OrderItems> convertsPayloadToTable(List<ItemPayload> payload) {
        List<OrderItems> orderItems = new ArrayList<>();
        if (payload != null) {
            payload.forEach(
                    el -> orderItems.add(OrderItems.builder().quantity(el.getQtd()).unitPrice(el.getPrecoUnitario())
                            .item(Items.builder().description(el.getDescricao()).build()).build()));
        }
        return orderItems;
    }

    public static PedidoResponse convertsTableToResponse(Orders orders) {
        PedidoResponse response = new PedidoResponse();
        if (orders != null) {
            List<ItemPayload> itemPayloads = new ArrayList<>();
            orders.getItems().forEach(el -> itemPayloads.add(ItemPayload.builder().precoUnitario(el.getUnitPrice())
                    .qtd(el.getQuantity()).descricao(el.getItem().getDescription()).build()));
            response = PedidoResponse.builder().pedido(orders.getId()).itens(itemPayloads).build();
        }
        return response;
    }

}
