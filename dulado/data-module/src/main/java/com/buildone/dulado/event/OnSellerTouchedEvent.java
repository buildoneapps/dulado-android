package com.buildone.dulado.event;

import com.buildone.dulado.model.SellerObject;

/**
 * Created by Alessandro Pryds on 22/05/2017.
 */

public class OnSellerTouchedEvent {
    private final SellerObject seller;

    public OnSellerTouchedEvent(SellerObject seller) {
        this.seller = seller;
    }

    public SellerObject getSeller() {
        return seller;
    }
}
