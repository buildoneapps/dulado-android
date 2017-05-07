package com.buildone.dulado.contracts;

import com.buildone.dulado.model.ProductObject;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 01/05/2017.
 */

public interface StoreContract {
    interface View{
        void initToolbar();
        void initProductsRecyclerView();
        void populateProductsRecyclerView(ArrayList<ProductObject> items);
        void loadMap(double latitude, double longitude);
        void setStoreOnwerName(String name);
        void setStoreOwnerCity(String city);
        void loadStoreOwnerPhoto(String url);
        void navigateToProductActivity(ProductObject object);
        void navigateToUserProfile(int sellerId);
        int getStoreId();
    }

    interface Presenter{
        void start();
        void onProductSelected(int position);
        void onButtonBookmarkTouched();
        void onPhoneButtonTouched();
        void onButtonProfileTouched();
        //void onRatingChanged()
    }
}
