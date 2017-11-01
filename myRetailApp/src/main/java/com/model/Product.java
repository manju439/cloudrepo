package com.model;

public class Product {

    private String id;
    private String name;
    private String currencyPrice;
    private String currencyCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(String currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

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
