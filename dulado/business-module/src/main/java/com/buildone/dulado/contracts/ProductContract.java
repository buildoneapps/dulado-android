package com.buildone.dulado.contracts;

import com.buildone.dulado.model.ProductObject;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public interface ProductContract {
    interface View{
        void initToolbar(String title, String subtitle);
        void setProductName(String name);
        void setProductTags(ArrayList<String> tags);
        void setProductDescription(String description);
        void setProductPrice(String price);
        void loadProductImages(ArrayList<String> imageUrls);
        void setProductQuantity();
        void setProductWish(boolean enabled);
        void showProductUnavailableContainer();
        void navigateToCheckoutActivity(ProductObject product);
        void navigateToChatActivity(ProductObject product);
        void navigateToStoreActivity(int storeId);
        ProductObject getProduct();
    }

    interface Presenter{
        void start();
        void onButtonCheckoutTouched();
        void onButtonChatTouched();
        void onButtonAddWishListTouched();
        void onButtonRemindMeTouched();
        void onButtonStoreTouched();
        void onIncreaseQuantity();
        void onDecreaseQuantity();
    }
}
