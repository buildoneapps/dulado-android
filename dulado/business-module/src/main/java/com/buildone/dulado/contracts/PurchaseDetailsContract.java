package com.buildone.dulado.contracts;

import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.view.BaseView;

/**
 * Created by Alessandro Pryds on 02/05/2017.
 */
//TODO: Add Google Location API
public interface PurchaseDetailsContract {
    interface View extends BaseView {
        void initToolbar();
        void setPurchaseStatus(int status);
        void setPurchasePriceText(String value);
        void setPurchaseNameText(String name);
        void setSellerNameText(String name);
        void loadSelledPhoto(String imageUrl);

        void hideMeetingContainer();
        void hideWaitScheduleContainer();
        void showPaymentContainer();
        void showRatingContainer();
        void showCodeContainer();

        void navigateToSellerProfile();
        void navigateToChat(ProductObject productObject);
        void navigateToPaymentActivity();
    }

    interface Presenter{
        void start();
        void onSellerDetailsTouched();
        void onPaymentButtonTouched();
        void onChatButtonTouched();
        void onLocationChanged(double latitude, double longitude);
        void onLocationFailed();

//        void onRatingChanged();

    }
}
