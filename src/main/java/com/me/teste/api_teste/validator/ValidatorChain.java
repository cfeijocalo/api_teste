package com.me.teste.api_teste.validator;

import java.util.List;

import com.me.teste.api_teste.util.StatusEnum;

public abstract class ValidatorChain<T, E> {

    private ValidatorChain<T, E> next;

    public ValidatorChain<T, E> linkWith(ValidatorChain<T, E> next) {
        this.next = next;
        return next;
    }

    public abstract List<StatusEnum> check(T payload, E entity, List<StatusEnum> status);

    protected List<StatusEnum> checkNext(T payload, E entity, List<StatusEnum> status) {
        if (next == null) {
            return status;
        }
        return next.check(payload, entity, status);
    }

}
