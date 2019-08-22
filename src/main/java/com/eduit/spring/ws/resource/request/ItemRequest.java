package com.eduit.spring.ws.resource.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ItemRequest {

    @JsonProperty("code")
    private Long id;
    @NotNull
    @JsonProperty("name")
    private String name;
    @NotNull
    @JsonProperty("price")
    private Float price;
    @NotNull
    @JsonProperty("quantity")
    private Integer quantity;

    @JsonCreator
    public ItemRequest(@JsonProperty("code")Long id,
                       @JsonProperty("name")String name,
                       @JsonProperty("price")Float price,
                       @JsonProperty("quantity")Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
