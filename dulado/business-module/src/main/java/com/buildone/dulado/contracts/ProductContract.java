package com.buildone.dulado.contracts;

import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.SearchObject;

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
        void setProductQuantity(int i);
        void setProductWish(boolean enabled);
        void setSellerName(String name);
        void setSellerPhoto(String photoUrl);
        void showProductUnavailableContainer();
        void navigateToCheckoutActivity(ProductObject product);
        void navigateToChatActivity(ProductObject product);
        void navigateToStoreActivity(int storeId);
        ProductObject getProduct();
        SearchObject getProductSearch();
    }

    interface Presenter{
        void start();
        void initSubscriptions();
        void disposeAll();
        void setupProduct(ProductObject product);
        void goToCheckout();
        void openChat();
        void addToWishList();
        void remindMe();
        void goToStore();
    }
}
