package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public class ProductObject {
    private int id;
    private int storeId;
    private String name;
    private float price;

    public ProductObject(int id, int storeId, String name, float price) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getStoreId() {
        return storeId;
    }
}
