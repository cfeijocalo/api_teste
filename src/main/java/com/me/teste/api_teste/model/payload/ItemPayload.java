package com.me.teste.api_teste.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPayload implements IPayload {

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private Integer precoUnitario;

    @Getter
    @Setter
    private Integer qtd;

}
