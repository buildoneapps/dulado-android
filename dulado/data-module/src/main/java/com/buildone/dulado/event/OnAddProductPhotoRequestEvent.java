package com.buildone.dulado.event;

/**
 * Created by Alessandro Pryds on 16/05/2017.
 */

public class OnAddProductPhotoRequestEvent {

    private final int position;

    public OnAddProductPhotoRequestEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
