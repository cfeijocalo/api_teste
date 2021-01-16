package com.me.teste.api_teste.model.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class ItemPayload {

    @Getter
    @Setter
    @NonNull
    private String descricao;

    @Getter
    @Setter
    @NonNull
    private Double precoUnitario;

    @Getter
    @Setter
    @NonNull
    private Integer qtd;

}
