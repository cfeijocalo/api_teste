package com.me.teste.api_teste.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.me.teste.api_teste.model.payload.StatusPayload;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.util.StatusEnum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PriceValidatorTest {

    StatusPayload payload;

    Orders entity;

    List<StatusEnum> status;

    ValidatorChain<StatusPayload, Orders> validator;

    @BeforeEach
    void setUp() {
        payload = StatusPayload.builder().status("APROVADO").pedido("123").valorAprovado(20).itensAprovados(3).build();
        entity = Orders.builder().id("123").totalPrice(20).totalQuantity(3).build();
        status = new ArrayList<>();
        validator = new PriceValidator();
    }

    @Test
    @DisplayName("When prices are same")
    void testSamePrice() {
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an empty array");
    }

    @Test
    @DisplayName("When one prices is greater")
    void testGreaterPrice() {
        payload.setValorAprovado(21);
        status.add(StatusEnum.APPROVED_GREATER_PRICE);
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an array with [APPROVED_GREATER_PRICE]");
    }

    @Test
    @DisplayName("When one price is lower")
    void testLowerPrice() {
        payload.setValorAprovado(19);
        status.add(StatusEnum.APPROVED_MINOR_PRICE);
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an array with [APPROVED_MINOR_PRICE]");
    }

}
