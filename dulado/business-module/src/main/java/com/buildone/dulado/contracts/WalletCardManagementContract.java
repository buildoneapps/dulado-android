package com.buildone.dulado.contracts;

import com.buildone.dulado.model.WalletCard;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 02/05/2017.
 */

public interface WalletCardManagementContract {
    interface View extends BaseView{
        void initToolbar();
        void initRecyclerView(ArrayList<WalletCard> items);
        void navigateToAddNewCard();
        void navigateToEditCard(WalletCard card);
    }

    interface Presenter{
        void start();
        void onCardSelected(int position);
        void onEditCardsTouched();
        void onCardSwitchChanged(int position, int activated);
        void onButtonEditCardsTouched();
        void onButtonAddNewCardTouched();
        void onButtonRemoveTouched(int position);
    }
}
