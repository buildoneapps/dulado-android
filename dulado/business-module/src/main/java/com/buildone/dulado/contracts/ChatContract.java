package com.buildone.dulado.contracts;

import com.buildone.dulado.model.ChatMessage;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 02/05/2017.
 */

public interface ChatContract {
    interface View extends BaseView{
        void initToolbar();
        void disableSendButton();
        void loadMap(double latitude, double longitude);
        void initRecyclerView(ArrayList<ChatMessage> items);
        void addMessage(ChatMessage message);
        void getProduct(ProductObject productObject);
        void setProductDescription(ProductObject product);
        void navigateToProductActivity(ProductObject product);
    }

    interface Presenter{
        void start();
        void initSubscriptions();
        void onButtonSendMessageTouched();
        void onButtonProductTouched();
    }
}
