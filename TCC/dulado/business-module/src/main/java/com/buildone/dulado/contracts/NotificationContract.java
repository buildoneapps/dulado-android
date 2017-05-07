package com.buildone.dulado.contracts;

import com.buildone.dulado.model.NotificationObject;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public interface NotificationContract {
    interface View{
        void initRecyclerView();
        void populateRecyclerView(ArrayList<NotificationObject> items);
    }

    interface Presenter{
        void start();
        void onNotificationSelected(int position);
    }
}
