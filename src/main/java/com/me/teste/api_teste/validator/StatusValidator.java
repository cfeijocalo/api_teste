package com.me.teste.api_teste.validator;

import java.util.List;

import com.me.teste.api_teste.model.payload.StatusPayload;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.util.StatusEnum;

public class StatusValidator extends ValidatorChain<StatusPayload, Orders> {

    @Override
    public List<StatusEnum> check(StatusPayload payload, Orders entity, List<StatusEnum> status) {
        if (payload.getStatus().equalsIgnoreCase(StatusEnum.DISAPPROVED.getMessage())) {
            status.add(StatusEnum.DISAPPROVED);
            return status;
        } else {
            return checkNext(payload, entity, status);
        }
    }

}
