package com.buildone.dulado.contracts;

import com.buildone.dulado.model.LiveObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */

public interface MainContract {

    interface View extends BaseView {
        void initToolbar();
        void initViewPager();
        void initLiveRecycler();
        void populateLiveRecycler(ArrayList<LiveObject> items);
        void navigateToStoreActivity(int store);
        int getSomeId();

        void switchToMap();
        void switchToList();
        void setMapButtonVisible(boolean visibile);
    }

    interface Presenter{
        void start();
        void onLiveItemTouched(int position);
        void onButtonMapTouched();
        void onButtonListTouched();
    }
}
