package com.buildone.logic.presenter.product;

import com.buildone.dulado.contracts.AddProductContract;
import com.buildone.dulado.interactor.IProductInteractor;

/**
 * Created by Alessandro Pryds on 06/05/2017.
 */

public class AddProductPresenter implements AddProductContract.Presenter {
    private AddProductContract.View view;
    private IProductInteractor interactor;

    public AddProductPresenter(AddProductContract.View view, IProductInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initToolbar();
    }

    @Override
    public void onButtonAddPhotoTouched() {

    }

    @Override
    public void onButtonPreviewTouched() {

    }

    @Override
    public void onButtonCameraTouched() {

    }

    @Override
    public void onButtonPhotoTouched() {

    }

    @Override
    public void onButtonGalleryTouched() {

    }

    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionError() {

    }
}
