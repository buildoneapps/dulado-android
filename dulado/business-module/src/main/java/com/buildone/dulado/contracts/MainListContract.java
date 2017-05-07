package com.buildone.dulado.contracts;

import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public interface MainListContract {
    interface View extends BaseView {
        void initRecyclerView();
        void populateRecyclerView(ArrayList<SearchObject> items);
    }

    interface Presenter{
        void start();
        void initSubscriptions();
        void unsubscribeAll();
        void onProductSelected(int position);
        void onChatProductTouched(int productPos);
    }
}
