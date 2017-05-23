package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 25/04/2017.
 */

public class SellerObject {
    private int id;
    private String name;
    private float rating;
    private String photoUrl;

    public SellerObject(int id, String photoUrl) {
        this.id = id;
        this.photoUrl = photoUrl;
    }

    public SellerObject() {
    }

    public String getPhotoUrl() {
        return photoUrl;
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
