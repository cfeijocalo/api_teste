package com.me.teste.api_teste.util;

import lombok.Getter;

public enum StatusEnum {
    INVALID_ORDER_NUMBER("CÓDIGO_PEDIDO_INVÁLIDO");

    @Getter
    private String message;

    StatusEnum(String message) {
        this.message = message;
    }

}
