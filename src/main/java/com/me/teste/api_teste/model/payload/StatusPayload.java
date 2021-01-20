package com.me.teste.api_teste.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusPayload implements IPayload {

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private Integer itensAprovados;

    @Getter
    @Setter
    private Integer valorAprovado;

    @Getter
    @Setter
    private String pedido;

}
