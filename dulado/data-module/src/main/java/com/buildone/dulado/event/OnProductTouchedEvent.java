package com.buildone.dulado.event;

import com.buildone.dulado.model.SearchObject;

/**
 * Created by Alessandro Pryds on 06/05/2017.
 */

public class OnProductTouchedEvent {
    private SearchObject productSearch;

    public OnProductTouchedEvent(SearchObject productSearch) {
        this.productSearch = productSearch;
    }

    public SearchObject getProductSearch() {
        return productSearch;
    }
}
