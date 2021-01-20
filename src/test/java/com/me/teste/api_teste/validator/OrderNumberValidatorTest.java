package com.me.teste.api_teste.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.util.StatusEnum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderNumberValidatorTest {

    PedidoPayload payload;

    Orders entity;

    List<StatusEnum> status;

    ValidatorChain<PedidoPayload, Orders> validator;

    static Stream<String> invalidValues() {
        return Stream.of("1234-N", "test_", "      ", "");
    }

    @BeforeEach
    void setUp() {
        payload = new PedidoPayload("123");
        entity = null;
        status = new ArrayList<>();
        validator = new OrderNumberValidator();
    }

    @Test
    @DisplayName("When order id is OK")
    void testOrderIdIsOK() {
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an empty array");
    }

    @ParameterizedTest
    @DisplayName("When order id is invalid")
    @MethodSource("invalidValues")
    void testOrderId(final String arg) {
        payload.setPedido(arg);
        status.add(StatusEnum.INVALID_ORDER_NUMBER);
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an array with [INVALID_ORDER_NUMBER]");
    }

    @Test
    @DisplayName("When payload is NULL")
    void testPayloadIsNull() {
        payload = null;
        status.add(StatusEnum.INVALID_ORDER_NUMBER);
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an array with [INVALID_ORDER_NUMBER]");
    }

}
