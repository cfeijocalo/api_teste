package com.me.teste.api_teste.model.response;

import java.util.List;

import com.me.teste.api_teste.model.IModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PedidoResponse implements IModel {

    @Getter
    @Setter
    private List<String> status;

}
