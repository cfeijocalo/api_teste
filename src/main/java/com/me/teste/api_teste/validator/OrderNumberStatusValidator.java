package com.me.teste.api_teste.validator;

import java.util.List;

import com.me.teste.api_teste.model.payload.StatusPayload;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.util.StatusEnum;

public class OrderNumberStatusValidator extends ValidatorChain<StatusPayload, Orders> {

    @Override
    public List<StatusEnum> check(StatusPayload payload, Orders entity, List<StatusEnum> status) {
        if (payload == null || payload.getPedido() == null || payload.getPedido().isEmpty()
                || !payload.getPedido().matches("\\d+")) {
            status.add(StatusEnum.INVALID_ORDER_NUMBER);
            return status;
        } else {
            return checkNext(payload, entity, status);
        }
    }

}
