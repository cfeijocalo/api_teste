package com.me.teste.api_teste.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements IResponse {

    @Getter
    @Setter
    private String error;

    @Getter
    @Setter
    private Integer status;

    @Getter
    @Setter
    private String message;

}
