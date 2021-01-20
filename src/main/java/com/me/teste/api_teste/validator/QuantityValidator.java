package com.me.teste.api_teste.validator;

import java.util.List;

import com.me.teste.api_teste.model.payload.StatusPayload;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.util.StatusEnum;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QuantityValidator extends ValidatorChain<StatusPayload, Orders> {

    @Override
    public List<StatusEnum> check(StatusPayload payload, Orders entity, List<StatusEnum> status) {
        if (payload.getItensAprovados() > entity.getTotalQuantity()) {
            status.add(StatusEnum.APPROVED_GREATER_QUANTITY);
            return checkNext(payload, entity, status);
        } else if (payload.getItensAprovados() < entity.getTotalQuantity()) {
            status.add(StatusEnum.APPROVED_MINOR_QUANTITY);
            return checkNext(payload, entity, status);
        } else {
            return checkNext(payload, entity, status);
        }
    }

}
