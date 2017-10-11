package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 08/10/2017.
 */

public class PendingSaleObject {
    private String productName;
    private String imageUrl;
    private BuyerModel buyer;
    private float price;
    private String tag;
    private int id;

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BuyerModel getBuyer() {
        return buyer;
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
