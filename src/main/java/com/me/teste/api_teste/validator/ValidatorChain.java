package com.me.teste.api_teste.validator;

import java.util.List;

import com.me.teste.api_teste.util.StatusEnum;

public abstract class ValidatorChain<T, S> {

    private ValidatorChain<T, S> next;

    public ValidatorChain<T, S> linkWith(ValidatorChain<T, S> next) {
        this.next = next;
        return next;
    }

    public abstract List<StatusEnum> check(T payload, S tables, List<StatusEnum> status);

    protected List<StatusEnum> checkNext(T payload, S tables, List<StatusEnum> status) {
        if (next == null) {
            return status;
        }
        return next.check(payload, tables, status);
    }

}
