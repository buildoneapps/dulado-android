package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 25/04/2017.
 */

public class SellerObject {
    private int id;
    private String name;
    private float rating;

    public SellerObject(int id) {
        this.id = id;
    }

    public SellerObject() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }
}
