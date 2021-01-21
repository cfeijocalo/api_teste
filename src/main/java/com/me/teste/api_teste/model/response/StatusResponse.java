package com.me.teste.api_teste.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StatusResponse implements IResponse {

    @Getter
    @Setter
    private String pedido;

    @Getter
    @Setter
    private List<String> status;

}
