package com.buildone.dulado.event;

import com.buildone.dulado.model.SearchObject;

/**
 * Created by Alessandro Pryds on 23/05/2017.
 */

public class OnProductAddedEvent {
    private final SearchObject productAdded;

    public OnProductAddedEvent(SearchObject productAdded) {
        this.productAdded = productAdded;
    }

    public SearchObject getProductAdded() {
        return productAdded;
    }
}
