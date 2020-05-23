package com.test.retail.model.dto;

import com.test.retail.model.Item;

public class ItemDto {

    private String name;
    private String type;
    private double price;

    public ItemDto(String name, String type, double price) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public ItemDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
