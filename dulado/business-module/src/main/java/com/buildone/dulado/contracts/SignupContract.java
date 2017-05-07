package com.buildone.dulado.contracts;

import com.buildone.dulado.model.UserObject;
import com.buildone.dulado.view.BaseView;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public interface SignupContract {
    interface View extends BaseView{
        void checkPermissions();
        void showUsernameError();
        void showPasswordError();
        void navigateToMainActivity(UserObject user);
    }

    interface Presenter{
        void start();
        void onPermissionGranted();
        void onPermissionFailed();
        void onButtonSingupTouched();
        void onButtonTermsTouched();
        void onButtonLoginTouched();
        void onButtonChangePhotoTouched();
    }
}
