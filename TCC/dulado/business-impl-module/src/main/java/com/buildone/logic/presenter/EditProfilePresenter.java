package com.buildone.logic.presenter;

import com.buildone.dulado.contracts.EditProfileContract;
import com.buildone.dulado.interactor.IUserInteractor;

/**
 * Created by Alessandro Pryds on 06/05/2017.
 */

public class EditProfilePresenter implements EditProfileContract.Presenter {
    private EditProfileContract.View view;
    private IUserInteractor interactor;

    public EditProfilePresenter(EditProfileContract.View view, IUserInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {

    }

    @Override
    public void onButtonSaveTouched() {

    }

    @Override
    public void onWalletTouched() {

    }

    @Override
    public void onOpenGalleryTouched() {

    }

    @Override
    public void onOpenCameraTouched() {

    }

    @Override
    public void onPermissionsGranted() {

    }

    @Override
    public void onPermissionsError() {

    }
}
