package com.order.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    public final String id;
    public final String title;
    public final double sellPrice;
    public final double price;
    public final String image;
    public final String categoryType;
    public final int stockQty;

    public Product(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty("sellPrice") double sellPrice,
            @JsonProperty("price") double price,
            @JsonProperty("image") String image,
            @JsonProperty("categoryType") String categoryType,
            @JsonProperty("stockQty") int stockQty) {
        this.id = id;
        this.title = title;
        this.sellPrice = sellPrice;
        this.price = price;
        this.image = image;
        this.categoryType = categoryType;
        this.stockQty = stockQty;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public int getStockQty() {
        return stockQty;
    }
}
