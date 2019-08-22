package com.eduit.spring.ws.resource.response;


import com.eduit.spring.ws.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemResponse {

    @JsonProperty("code")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Float price;
    @JsonProperty("quantity")
    private Integer quantity;


    public ItemResponse(Long id, String name, Float price, Integer quantity) {
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

    public static ItemResponse from (Item item){
        return new ItemResponse(item.getId(),item.getName(),item.getPrice(),item.getQuantity());
    }
}
