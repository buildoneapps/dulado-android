package com.buildone.dulado.model;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public class ProductObject {
    private int id;
    private int storeId;
    private String name;
    private float price;
    private String description;
    private String tags;
    private ArrayList<String> productImages;
    private SellerObject seller;
    private int storeQuantity;

    public ProductObject() {
    }

    public ProductObject(int id, int storeId, float price, String name, String description, ArrayList<String> productImages, SellerObject seller, String tags, int storeQuantity) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.price = price;
        this.productImages = productImages;
        this.description = description;
        this.seller = seller;
        this.tags = tags;
        this.storeQuantity = storeQuantity;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public ArrayList<String> getProductImages() {
        return productImages;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SellerObject getSeller() {
        return seller;
    }

    public float getPrice() {
        return price;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getStoreQuantity() {
        return storeQuantity;
    }

    public void setStoreQuantity(int storeQuantity) {
        this.storeQuantity = storeQuantity;
    }
}
