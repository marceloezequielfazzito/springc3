package com.eduit.spring.ws.resource.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class PriceRequest {

    @JsonProperty("code")
    @NotNull
    private Long code;
    @JsonProperty("price")
    @NotNull
    private Float price;

    @JsonCreator
    public PriceRequest( @JsonProperty("code")Long code,
                         @JsonProperty("price")Float price) {
        this.code = code;
        this.price = price;
    }




    public Float getPrice() {
        return price;
    }

    public Long getCode() {
        return code;
    }
}
