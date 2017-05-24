package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 22/04/2017.
 */

public class LiveObject {
    private int userId;
    private String photoUrl;
    private int storeId;

    public LiveObject(int userId, int storeId, String photoUrl) {
        this.userId = userId;
        this.photoUrl = photoUrl;
        this.storeId = storeId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getStoreId() {
        return storeId;
    }
}
