package com.me.teste.api_teste.model.response;

import java.util.List;

import com.me.teste.api_teste.model.IModel;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class StatusResponse implements IModel {

    @Getter
    @Setter
    @NonNull
    private String pedido;

    @Getter
    @Setter
    @NonNull
    private List<String> status;

}
