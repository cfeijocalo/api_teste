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
public class StatusValidatorTest {

    StatusPayload payload;

    Orders entity;

    List<StatusEnum> status;

    ValidatorChain<StatusPayload, Orders> validator;

    @BeforeEach
    void init() {
        payload = StatusPayload.builder().status("APROVADO").pedido("123").valorAprovado(20).itensAprovados(3).build();
        entity = null;
        status = new ArrayList<>();
        validator = new StatusValidator();
    }

    @Test
    @DisplayName("When status is APPROVED")
    void testApprovedStatus() {
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an empty array");
    }

    @Test
    @DisplayName("When status is DISAPPROVED")
    void testDisapprovedStatus() {
        payload.setStatus("REPROVADO");
        status.add(StatusEnum.DISAPPROVED);
        assertEquals(status, validator.check(payload, entity, new ArrayList<StatusEnum>()),
                "Should return an array with [DISAPPROVED]");
    }

}
