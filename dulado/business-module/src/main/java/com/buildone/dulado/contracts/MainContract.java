package com.buildone.dulado.contracts;

import com.buildone.dulado.model.LiveObject;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.model.SellerObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;
import java.util.List;

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
        void navigateToProductActivity(SearchObject searchObject);
        void navigateToChatActivity(SearchObject productId, String s);
        void navigateToSearchActivity();
        void navigateToAddProductActivity(String photoUri);

        void switchToMap(boolean inGridMode);
        void switchToList(boolean inGridMode);
        void setMapButtonVisible(boolean visibile);
        void setLiveRecyclerVisible(boolean visible);
        void setLiveProgressVisible(boolean visible);

        void showCreateAdButton();
        void hideCreateAdButton();

        int getSomeId();

        void checkCameraPermission();
        void showCameraPermissionRequiredMessage();

        void openCamera();
        void showCouldNotTakePhotoMessage();

        void setupBottomNavigation();

    }

    interface Presenter{
        void start();
        void initSubscriptions();
        void disposeAll();
        void onScrollChanged(boolean isScrolling);
        void onLiveItemTouched(int position);
        void onButtonMapTouched();
        void onProductSelected(SearchObject product);
        void onChatProductTouched(SearchObject product);
        void onSellerTouched(SellerObject seller);
        void onButtonListTouched();
        void onButtonGridTouched();
        void onButtonSearchTouched();
        void onButtonCreateAdTouched();

        void onPermissionGranted(String[] receivedPermissions, List<String> required);
        void onPermissionFailed();

        void navigateToAddProduct(String photoUri);

        void cameraError();

        void addProduct(SearchObject productAdded);

        void openCamera();
    }
}
