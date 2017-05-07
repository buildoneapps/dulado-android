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
        void navigateToSearchActivity();

        void switchToMap(boolean inGridMode);
        void switchToList(boolean inGridMode);
        void setMapButtonVisible(boolean visibile);
        void setLiveRecyclerVisible(boolean visible);

        int getSomeId();
    }

    interface Presenter{
        void start();
        void onLiveItemTouched(int position);
        void onButtonMapTouched();
        void onButtonListTouched();
        void onButtonGridTouched();
        void onButtonSearchTouched();
    }
}
