package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 25/04/2017.
 */

public class SearchObject {
    private String productName;
    private String imageUrl;
    private SellerObject seller;
    private float price;
    private String tag;
    private int id;

    public SearchObject() {
    }

    public SearchObject(int id, String productName, String imageUrl, SellerObject seller, float price, String tag) {
        this.imageUrl = imageUrl;
        this.id = id;
        this.seller = seller;
        this.price = price;
        this.tag = tag;
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public SellerObject getSeller() {
        return seller;
    }

    public float getPrice() {
        return price;
    }

    public String getTag() {
        return tag;
    }

    public int getId() {
        return id;
    }
}
