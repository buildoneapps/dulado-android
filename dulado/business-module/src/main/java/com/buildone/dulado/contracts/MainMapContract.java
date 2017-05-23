package com.buildone.dulado.contracts;

import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public interface MainMapContract {

    interface View extends BaseView {
        void initMap();
        void initLocationService();
        void initPermissions();

        void loadMapStyle();
        void enableMapInteraction();
        void centerUser();
        void showRadius(int radius);
        void initStoresScrollView();
        void populateStoreScrollView(ArrayList<SearchObject> items);
        void hideStoresScrollView();
        void showStoresScrollView();
    }

    interface Presenter{
        void start();
        void initSubscriptions();
        void disposeAll();
        void onPermissionFailed();
        void onPermissionGranted(String[] receivedPermissions, List<String> required);
        void onMapReady();
        void onGoogleConnected();
        void onButtonHideStoresTouched();
        void onCameraMove();
    }
}
