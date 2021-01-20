package com.me.teste.api_teste.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.me.teste.api_teste.model.payload.ItemPayload;
import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.response.PedidoResponse;
import com.me.teste.api_teste.model.table.Items;
import com.me.teste.api_teste.model.table.OrderItems;
import com.me.teste.api_teste.model.table.Orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModelConverterTest {

    PedidoPayload payload;

    Orders order;

    PedidoResponse response;

    @BeforeEach
    void setUp() {
        List<ItemPayload> itens = new ArrayList<>();
        itens.add(ItemPayload.builder().descricao("ITEM 1").qtd(2).precoUnitario(3).build());
        itens.add(ItemPayload.builder().descricao("ITEM 2").qtd(3).precoUnitario(5).build());

        payload = new PedidoPayload("123", itens);

        List<OrderItems> items = new ArrayList<>();
        items.add(OrderItems.builder().quantity(2).unitPrice(3).item(Items.builder().description("ITEM 1").build())
                .build());
        items.add(OrderItems.builder().quantity(3).unitPrice(5).item(Items.builder().description("ITEM 2").build())
                .build());

        order = Orders.builder().id("123").totalPrice(21).totalQuantity(5).items(items).build();

        response = PedidoResponse.builder().pedido("123").itens(itens).build();
    }

    @Test
    @DisplayName("When converts PedidoPayload to Orders")
    void testConvertsPayloadToTable() {
        assertTrue(ModelConverter.convertsPayloadToTable(payload) instanceof Orders, "Should return a order");
        assertEquals(order, ModelConverter.convertsPayloadToTable(payload),
                "Should return a equal order after convert");
    }

    @Test
    @DisplayName("When converts ItemPayload list to a OrderItems list")
    void testConvertsListItemPayloadToListOrderItems() {
        assertTrue(ModelConverter.convertsPayloadToTable(payload.getItens()) instanceof List, "Should return a List");
        assertTrue(ModelConverter.convertsPayloadToTable(payload.getItens()).get(0) instanceof OrderItems,
                "Should be a OrderList");
        assertEquals(order.getItems(), ModelConverter.convertsPayloadToTable(payload.getItens()),
                "Should return a equal list of OrderItems after convert");
    }

    @Test
    @DisplayName("When converts Orders to PedidoResponse")
    void testConvertsOrdersToPedidoResponse() {
        assertTrue(ModelConverter.convertsTableToResponse(order) instanceof PedidoResponse,
                "Should be a PedidoResponse");
        assertEquals(response, ModelConverter.convertsTableToResponse(order),
                "Should return a equal response after converter");
    }

}
