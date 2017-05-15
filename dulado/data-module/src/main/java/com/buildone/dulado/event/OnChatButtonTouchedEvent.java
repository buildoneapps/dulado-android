package com.buildone.dulado.event;

import com.buildone.dulado.model.SearchObject;

/**
 * Created by Alessandro Pryds on 08/05/2017.
 */

public class OnChatButtonTouchedEvent {
    private SearchObject searchProduct;

    /**
     * When user touch chat button from a searchProduct
     * @param searchProduct selected
     */
    public OnChatButtonTouchedEvent(SearchObject searchProduct) {
        this.searchProduct = searchProduct;
    }

    public SearchObject getSearchProduct() {
        return searchProduct;
    }
}
