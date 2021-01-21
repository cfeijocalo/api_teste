package com.me.teste.api_teste.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.me.teste.api_teste.model.payload.StatusPayload;
import com.me.teste.api_teste.model.response.StatusResponse;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.repository.OrdersRepository;
import com.me.teste.api_teste.util.StatusEnum;
import com.me.teste.api_teste.validator.OrderNumberStatusValidator;
import com.me.teste.api_teste.validator.PriceValidator;
import com.me.teste.api_teste.validator.QuantityValidator;
import com.me.teste.api_teste.validator.StatusValidator;
import com.me.teste.api_teste.validator.ValidatorChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusService extends Service<StatusPayload, Orders> {

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    protected List<String> validate(StatusPayload payload, Orders entity) {
        ValidatorChain<StatusPayload, Orders> validator = new StatusValidator();
        validator.linkWith(new QuantityValidator()).linkWith(new PriceValidator());
        List<StatusEnum> status = validator.check(payload, entity, new ArrayList<>());
        if (!status.isEmpty()) {
            List<String> statusMessage = new ArrayList<>();
            status.forEach(element -> statusMessage.add(element.getMessage()));
            return statusMessage;
        }
        return new ArrayList<>();
    }

    @Override
    protected Orders findOrder(String id) throws IllegalArgumentException {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            return null;
        }
    }

    private List<String> validateOrderId(StatusPayload payload) {
        ValidatorChain<StatusPayload, Orders> validator = new OrderNumberStatusValidator();
        List<StatusEnum> status = validator.check(payload, null, new ArrayList<>());
        if (!status.isEmpty()) {
            List<String> statusMessage = new ArrayList<>();
            status.forEach(element -> statusMessage.add(element.getMessage()));
            return statusMessage;
        }
        return new ArrayList<>();
    }

    public ResponseEntity<StatusResponse> updateStatus(StatusPayload status) {
        if (status != null) {
            List<String> statusList = validateOrderId(status);
            if (statusList.contains(StatusEnum.INVALID_ORDER_NUMBER.getMessage())) {
                return new ResponseEntity<>(
                        StatusResponse.builder().pedido(status.getPedido()).status(statusList).build(),
                        HttpStatus.BAD_REQUEST);
            } else {
                Orders order = findOrder(status.getPedido());
                if (order != null) {
                    statusList = validate(status, order);
                    if (statusList.isEmpty()) {
                        return new ResponseEntity<>(
                                StatusResponse.builder().pedido(status.getPedido())
                                        .status(Arrays.asList(StatusEnum.APPROVED.getMessage())).build(),
                                HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(
                                StatusResponse.builder().pedido(status.getPedido()).status(statusList).build(),
                                HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<>(
                            StatusResponse.builder().pedido(status.getPedido())
                                    .status(Arrays.asList(StatusEnum.INVALID_ORDER_NUMBER.getMessage())).build(),
                            HttpStatus.NOT_FOUND);
                }
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
