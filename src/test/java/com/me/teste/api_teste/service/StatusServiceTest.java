package com.me.teste.api_teste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import com.me.teste.api_teste.model.payload.StatusPayload;
import com.me.teste.api_teste.model.response.StatusResponse;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.repository.OrdersRepository;
import com.me.teste.api_teste.util.StatusEnum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class StatusServiceTest {

    @InjectMocks
    StatusService service;

    @Mock
    OrdersRepository repository;

    Optional<Orders> order;

    StatusPayload payload;

    AutoCloseable closeable;

    @BeforeEach
    void init() {
        closeable = MockitoAnnotations.openMocks(this);
        order = Optional.of(Orders.builder().id("123456").totalPrice(20).totalQuantity(3).build());
        payload = StatusPayload.builder().status("APROVADO").pedido("123456").valorAprovado(20).itensAprovados(3)
                .build();
    }

    @AfterEach
    void end() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("When totalQuantity, totalPrice are same than order and status APROVADO")
    void testApproved() {
        when(repository.findById("123456")).thenReturn(order);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.APPROVED.getMessage())).build();
        assertEquals(expected, response.getBody(), "Should have status ".concat(StatusEnum.APPROVED.getMessage()));
    }

    @Test
    @DisplayName("When totalPrice is greater and status APROVADO")
    void testPriceGreater() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setValorAprovado(21);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.APPROVED_GREATER_PRICE.getMessage())).build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.APPROVED_GREATER_PRICE.getMessage()));
    }

    @Test
    @DisplayName("When totalPrice is lower and status APROVADO")
    void testPriceLower() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setValorAprovado(19);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.APPROVED_MINOR_PRICE.getMessage())).build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.APPROVED_MINOR_PRICE.getMessage()));
    }

    @Test
    @DisplayName("When totalQuantity is greater and status APROVADO")
    void testQuantityGreater() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setItensAprovados(4);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.APPROVED_GREATER_QUANTITY.getMessage())).build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.APPROVED_GREATER_QUANTITY.getMessage()));
    }

    @Test
    @DisplayName("When totalQuantity is lower and status APROVADO")
    void testQuantityLower() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setItensAprovados(2);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.APPROVED_MINOR_QUANTITY.getMessage())).build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.APPROVED_MINOR_QUANTITY.getMessage()));
    }

    @Test
    @DisplayName("When totalQuantity, totalPrice are greater and status APROVADO")
    void testQuantityAndPriceGreater() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setItensAprovados(4);
        payload.setValorAprovado(21);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.APPROVED_GREATER_QUANTITY.getMessage(),
                        StatusEnum.APPROVED_GREATER_PRICE.getMessage()))
                .build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.APPROVED_GREATER_QUANTITY.getMessage()).concat(" and ")
                        .concat(StatusEnum.APPROVED_GREATER_PRICE.getMessage()));
    }

    @Test
    @DisplayName("When totalQuantity, totalPrice are lower and status APROVADO")
    void testQuantityAndPriceLower() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setItensAprovados(2);
        payload.setValorAprovado(19);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456").status(Arrays
                .asList(StatusEnum.APPROVED_MINOR_QUANTITY.getMessage(), StatusEnum.APPROVED_MINOR_PRICE.getMessage()))
                .build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.APPROVED_MINOR_QUANTITY.getMessage()).concat(" and ")
                        .concat(StatusEnum.APPROVED_MINOR_PRICE.getMessage()));
    }

    @Test
    @DisplayName("When totalQuantity is lower, totalPrice is greater and status APROVADO")
    void testQuantityLowerAndPriceGreater() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setItensAprovados(2);
        payload.setValorAprovado(21);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.APPROVED_MINOR_QUANTITY.getMessage(),
                        StatusEnum.APPROVED_GREATER_PRICE.getMessage()))
                .build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.APPROVED_MINOR_QUANTITY.getMessage()).concat(" and ")
                        .concat(StatusEnum.APPROVED_GREATER_PRICE.getMessage()));
    }

    @Test
    @DisplayName("When totalQuantity is greater, totalPrice is lower and status APROVADO")
    void testQuantityGreaterAndPriceLower() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setItensAprovados(4);
        payload.setValorAprovado(19);
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.APPROVED_GREATER_QUANTITY.getMessage(),
                        StatusEnum.APPROVED_MINOR_PRICE.getMessage()))
                .build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.APPROVED_GREATER_QUANTITY.getMessage()).concat(" and ")
                        .concat(StatusEnum.APPROVED_MINOR_PRICE.getMessage()));
    }

    @Test
    @DisplayName("When status is REPROVADO")
    void testDisapprove() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setStatus(StatusEnum.DISAPPROVED.getMessage());
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456")
                .status(Arrays.asList(StatusEnum.DISAPPROVED.getMessage())).build();
        assertEquals(expected, response.getBody(), "Should have status ".concat(StatusEnum.DISAPPROVED.getMessage()));
    }

    @Test
    @DisplayName("When is a invalid order id")
    void testInvalidId() {
        when(repository.findById(anyString())).thenReturn(order);
        payload.setPedido("123456-N");
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("123456-N")
                .status(Arrays.asList(StatusEnum.INVALID_ORDER_NUMBER.getMessage())).build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.INVALID_ORDER_NUMBER.getMessage()));
    }

    @Test
    @DisplayName("When not found order")
    void testNotFoundOrder() {
        when(repository.findById("123456")).thenReturn(order);
        payload.setPedido("12345");
        ResponseEntity<StatusResponse> response = service.updateStatus(payload);
        StatusResponse expected = StatusResponse.builder().pedido("12345")
                .status(Arrays.asList(StatusEnum.INVALID_ORDER_NUMBER.getMessage())).build();
        assertEquals(expected, response.getBody(),
                "Should have status ".concat(StatusEnum.INVALID_ORDER_NUMBER.getMessage()));
    }

}
