package com.buildone.dulado.contracts;

import com.buildone.dulado.model.UserObject;
import com.buildone.dulado.view.BaseView;

/**
 * Created by Alessandro Pryds on 02/05/2017.
 */

public interface EditProfileContract {
    interface View extends BaseView{
        void initToolbar();
        void loadUserPhoto(String imageUrl);
        void setUserDetailText(UserObject user);
        void navigateToWalletManager();
        void showPhotoPickerChooser();
        void checkPermissions();
        void openCamera();
        void openGallery();
        //void highlightWrongFields();
    }

    interface Presenter {
        void start();
        void onButtonSaveTouched();
        void onWalletTouched();
        void onOpenGalleryTouched();
        void onOpenCameraTouched();
        void onPermissionsGranted();
        void onPermissionsError();
    }
}
