package com.buildone.logic.presenter;

import com.buildone.dulado.contracts.UserProfileContract;
import com.buildone.dulado.interactor.IUserInteractor;

/**
 * Created by Alessandro Pryds on 04/05/2017.
 */

public class UserProfilePresenter implements UserProfileContract.Presenter {
    private UserProfileContract.View view;
    private IUserInteractor userInteractor;
    private int userId;

    public UserProfilePresenter(UserProfileContract.View view, IUserInteractor userInteractor, int userId) {
        this.view = view;
        this.userInteractor = userInteractor;
        this.userId = userId;
    }

    @Override
    public void start() {
        view.initToolbar();
    }

    @Override
    public void onFollowButtonTouched() {

    }

    @Override
    public void onPhotoTouched() {

    }
}
