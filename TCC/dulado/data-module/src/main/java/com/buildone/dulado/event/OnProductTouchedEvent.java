package com.buildone.dulado.event;

import com.buildone.dulado.model.ProductObject;

/**
 * Created by Alessandro Pryds on 06/05/2017.
 */

public class OnProductTouchedEvent {
    private ProductObject productObject;

    public OnProductTouchedEvent(ProductObject productObject) {
        this.productObject = productObject;
    }

    public ProductObject getProduct() {
        return productObject;
    }
}
