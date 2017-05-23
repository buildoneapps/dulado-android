package com.buildone.dulado.contracts;

import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public interface MainListContract {
    interface View extends BaseView {
        void initListRecyclerView();
        void populateListRecyclerView(ArrayList<SearchObject> items);
        void initGridRecyclerView();
        void populateGridRecyclerView(ArrayList<SearchObject> items);
        void navigateToProductActivity(SearchObject product);
        void navigateToChatActivity(int productId);
        void showEmptyMessage();
    }

    interface Presenter{
        void start();
        void loadProducts();
        void initSubscriptions();
        void unsubscribeAll();
        void onProductSelected(SearchObject product);
        void onChatProductTouched(SearchObject product);
        void switchListMode(int listFormat);

        void onShow();

        void onHide();
    }
}
