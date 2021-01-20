package com.me.teste.api_teste.model.response;

import java.util.List;

import com.me.teste.api_teste.model.payload.ItemPayload;
import com.me.teste.api_teste.model.payload.PedidoPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoResponse extends PedidoPayload implements IResponse {

    @Getter
    @Setter
    private List<String> status;

    @Builder
    public PedidoResponse(String pedido, List<ItemPayload> itens, List<String> status) {
        super(pedido, itens);
        this.status = status;
    }

}
