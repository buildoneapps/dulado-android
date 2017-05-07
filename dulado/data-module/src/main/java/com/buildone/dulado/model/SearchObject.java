package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 25/04/2017.
 */

public class SearchObject {
    private String imageUrl;
    private SellerObject seller;
    private float price;
    private String tag;

    public SearchObject(String imageUrl, SellerObject seller, float price, String tag) {
        this.imageUrl = imageUrl;
        this.seller = seller;
        this.price = price;
        this.tag = tag;
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
}
