package com.buildone.dulado.contracts;

import com.buildone.dulado.model.PaymentOptions;
import com.buildone.dulado.model.WalletCard;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 02/05/2017.
 */

public interface PaymentMethodContract {
    interface View{
        void initToolbar();
        void showPaymentOptions(ArrayList<PaymentOptions> options);
        void initCardView(WalletCard card);
        void navigateToWalletCardManager();
    }

    interface Presenter{
        void onCardTouched();
        void onPaymentOptionTouched(int position);
    }
}
