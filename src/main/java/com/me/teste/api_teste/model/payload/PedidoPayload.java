package com.me.teste.api_teste.model.payload;

import java.util.List;

import com.me.teste.api_teste.model.IModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PedidoPayload implements IModel {

    @Getter
    @Setter
    private String pedido;

    @Getter
    @Setter
    private List<ItemPayload> itens;

}
