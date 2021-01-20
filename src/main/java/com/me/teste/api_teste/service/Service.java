package com.me.teste.api_teste.service;

import java.util.List;

import com.me.teste.api_teste.model.table.Orders;

public abstract class Service<T, E> {

    protected abstract List<String> validate(T payload, E entity);

    protected abstract Orders findOrder(String id) throws IllegalArgumentException;

}
