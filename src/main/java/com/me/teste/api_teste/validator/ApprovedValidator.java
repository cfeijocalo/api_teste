package com.me.teste.api_teste.validator;

import java.util.List;

import com.me.teste.api_teste.model.payload.StatusPayload;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.util.StatusEnum;

public class ApprovedValidator extends ValidatorChain<StatusPayload, Orders> {

    @Override
    public List<StatusEnum> check(StatusPayload payload, Orders entity, List<StatusEnum> status) {
        if (payload.getStatus().equals(StatusEnum.DISAPPROVED.getMessage())) {
            status.add(StatusEnum.DISAPPROVED);
            return status;
        } else {
            return checkNext(payload, entity, status);
        }
    }

}
