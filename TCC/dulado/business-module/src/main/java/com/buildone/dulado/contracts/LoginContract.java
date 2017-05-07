package com.buildone.dulado.contracts;

import com.buildone.dulado.model.UserObject;
import com.buildone.dulado.view.BaseView;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public interface LoginContract {
    interface View extends BaseView{
        void showUsernameError();
        void showPasswordError();
        void navigateToMainActivity(UserObject user);
    }

    interface Presenter{
        void onButtonLoginTouched();
        void onButtonForgotPasswordTouched();
        void onButtonSignupTouched();
        void onButtonLoginWithFacebookTouched();
        void onButtonLoginWithGoogleTouched();
    }
}
