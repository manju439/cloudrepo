package com.model;

import lombok.Data;

@Data
public class Product {

    private String id;
    private String name;
    private String currencyPrice;
    private String currencyCode;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\""+
                ", \"currency_price\":{ \"value\"" + currencyPrice + "\""+
                ",\" currency_code\":\"" + currencyCode + "\""+
                "}}";
    }
}
