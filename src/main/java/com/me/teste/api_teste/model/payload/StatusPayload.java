package com.me.teste.api_teste.model.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class StatusPayload {

    @Getter
    @Setter
    @NonNull
    private String status;

    @Getter
    @Setter
    @NonNull
    private Integer itensAprovados;

    @Getter
    @Setter
    @NonNull
    private Double valorAprovado;

    @Getter
    @Setter
    @NonNull
    private Integer pedido;

}
