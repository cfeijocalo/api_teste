package com.me.teste.api_teste.model.response;

import java.util.List;

import com.me.teste.api_teste.model.IModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class StatusResponse implements IModel {

    @Getter
    @Setter
    private String pedido;

    @Getter
    @Setter
    private List<String> status;

}
