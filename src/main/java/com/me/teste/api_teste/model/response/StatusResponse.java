package com.me.teste.api_teste.model.response;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class StatusResponse {

    @Getter
    @Setter
    @NonNull
    private Long pedido;

    @Getter
    @Setter
    @NonNull
    private List<String> status;

}
