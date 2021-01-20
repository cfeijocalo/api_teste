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
public class QuantityValidatorTest {

    StatusPayload payload;

    Orders entity;

    List<StatusEnum> status;

    ValidatorChain<StatusPayload, Orders> validator;

    @BeforeEach
    void init() {
        payload = StatusPayload.builder().status("APROVADO").pedido("123").valorAprovado(20).itensAprovados(3).build();
        entity = Orders.builder().id("123").totalPrice(20).totalQuantity(3).build();
        status = new ArrayList<>();
        validator = new QuantityValidator();
    }

    @Test
    @DisplayName("When quantities are same")
    void testSameQuantity() {
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an empty array");
    }

    @Test
    @DisplayName("When one quantity is greater")
    void testGreaterQuantity() {
        payload.setItensAprovados(4);
        status.add(StatusEnum.APPROVED_GREATER_QUANTITY);
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an array with [APPROVED_GREATER_QUANTITY]");
    }

    @Test
    @DisplayName("When one quantity is lower")
    void testLowerQuantity() {
        payload.setItensAprovados(2);
        status.add(StatusEnum.APPROVED_MINOR_QUANTITY);
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an array with [APPROVED_MINOR_QUANTITY]");
    }

}
