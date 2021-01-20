package com.me.teste.api_teste.model.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PedidoPayload implements IPayload {

    @Getter
    @Setter
    @NonNull
    private String pedido;

    @Getter
    @Setter
    private List<ItemPayload> itens;

}
