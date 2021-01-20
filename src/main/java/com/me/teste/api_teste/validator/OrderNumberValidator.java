package com.me.teste.api_teste.validator;

import java.util.List;

import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.util.StatusEnum;

public class OrderNumberValidator extends ValidatorChain<PedidoPayload, Orders> {

    @Override
    public List<StatusEnum> check(PedidoPayload payload, Orders entity, List<StatusEnum> status) {
        if (payload != null && !payload.getPedido().matches("\\d+")) {
            status.add(StatusEnum.INVALID_ORDER_NUMBER);
            return status;
        } else {
            return checkNext(payload, entity, status);
        }
    }
}
