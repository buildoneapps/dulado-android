package com.buildone.dulado.contracts;

/**
 * Created by Alessandro Pryds on 07/06/2017.
 */

public interface CheckoutOverviewContract {
    interface View{
        void initToolbar();
        void setProductName(String productName);
        void setProductTag(String tags);
        void setQuantity(int quantity);

        void setShippingSelectedStyle();
        void setPaymentSelectedStyle();
        void openStatus(int status);
        void closeStatus(int status);

        void hidePaymentMethod();
        void showPaymentSelected();

        void showPaymentButton();
        void hidePaymentButton();

        void loadMap();
    }

    interface Presenter{
        void start();
        void selectPickup();
        void selectDelivery();
        void selectPaymentMethod();
        void confirmOrder();
    }
}
