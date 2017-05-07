package com.buildone.dulado.event;

import com.buildone.dulado.model.StoreObject;

/**
 * Created by Alessandro Pryds on 06/05/2017.
 */

public class OnStoreTouchedEvent {
    private StoreObject storeObject;

    public OnStoreTouchedEvent(StoreObject storeObject) {
        this.storeObject = storeObject;
    }

    public StoreObject getStore() {
        return storeObject;
    }
}
