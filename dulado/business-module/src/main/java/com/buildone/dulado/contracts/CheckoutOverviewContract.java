package com.buildone.dulado.contracts;

/**
 * Created by Alessandro Pryds on 07/06/2017.
 */

public interface CheckoutOverviewContract {
    interface View{
        void initToolbar();
        void setProductName(String productName);
        void setProductTag(String tags);
        void setProductPhoto(String photoUrl);
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

        void setTotal(float value);
        void setSubtotal(float value);
        void setShippingPrice(float value);
        void setDiscount(float value);

        void showCouponProgress();
        void hideCouponProgress();

        void showCouponConfirmedIcon();

        void blockShippingButtons();
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
        void attemptToApplyCoupon();
    }
}
