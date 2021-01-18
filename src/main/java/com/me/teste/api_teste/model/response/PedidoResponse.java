package com.me.teste.api_teste.model.response;

import java.util.List;

import com.me.teste.api_teste.model.payload.ItemPayload;
import com.me.teste.api_teste.model.payload.PedidoPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse extends PedidoPayload {

    @Getter
    @Setter
    private List<String> status;

    @Builder
    public PedidoResponse(String pedido, List<ItemPayload> itens, List<String> status) {
        super(pedido, itens);
        this.status = status;
    }

}
