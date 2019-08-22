package com.eduit.spring.ws.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceResponse {

    @JsonProperty("price")
    private Float price;

    public PriceResponse(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }
}
