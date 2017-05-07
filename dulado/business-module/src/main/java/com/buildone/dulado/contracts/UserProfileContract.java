package com.buildone.dulado.contracts;

import com.buildone.dulado.model.UserObject;
import com.buildone.dulado.view.BaseView;

/**
 * Created by Alessandro Pryds on 04/05/2017.
 */

public interface UserProfileContract {
    interface View extends BaseView {
        void initToolbar();
        void loadUserData(UserObject user);
        void expandPhoto();

        int getUserId();
    }

    interface Presenter {
        void start();
        void onFollowButtonTouched();
        void onPhotoTouched();
    }
}
