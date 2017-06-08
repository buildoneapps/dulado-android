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

        void setDeliverySelected(boolean selected);
        void setPickupSelected(boolean selected);
        void setPaymentSelectedStyle(int paymentMethod);
        void openStatus(int status);
        void closeStatus(int status);

        void hidePaymentMethod();
        void showPaymentSelected();

        void showPaymentButton();
        void hidePaymentButton();

        void loadMap();

        void setIncreaseButtonEnabled(boolean enable);
        void setDecreaseButtonEnabled(boolean enable);

        void showSecurePaymentDialog();
        void hideSecurePaymentDialog();

        void setConfirmOrderProgressVisible(boolean visible);

        void uncheckAllPaymentMethods();

        void showCouponEditText();
        void hideCouponContainer();
    }

    interface Presenter{
        void start();
        void selectPickup();
        void selectDelivery();
        void selectPaymentMethod(int methodId);
        void nextStep();
        void confirmOrder();
        void increaseQuantity();
        void decreaseQuantity();
        void insertCupon();
        void reviewProduct();
        void attemptToPurchase();
    }
}
