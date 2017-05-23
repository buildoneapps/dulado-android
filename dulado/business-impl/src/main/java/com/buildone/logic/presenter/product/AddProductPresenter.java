package com.buildone.logic.presenter.product;

import com.buildone.dulado.contracts.AddProductContract;
import com.buildone.dulado.interactor.IProductInteractor;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Alessandro Pryds on 06/05/2017.
 */

public class AddProductPresenter implements AddProductContract.Presenter {
    private AddProductContract.View view;
    private IProductInteractor interactor;
    private CompositeDisposable disposable;
    private ArrayList<String> items;
    private int selectedPosition;

    public AddProductPresenter(AddProductContract.View view, IProductInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        this.items = new ArrayList<String>(){{
            add("");
            add("");
            add("");
            add("");
            add("");
        }};
    }

    @Override
    public void start() {
        view.initToolbar();
        view.initPhotosRecyclerView(items);
        initSubscriptions();
    }

    @Override
    public void initSubscriptions() {
        disposable = new CompositeDisposable();
    }

    @Override
    public void disposeAll() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onButtonAddPhotoTouched(int position) {
        selectedPosition = position;
        if(items.get(position).isEmpty()){
            view.openCamera();
        }
    }

    @Override
    public void onButtonPreviewTouched() {

    }

    @Override
    public void onButtonCameraTouched() {
        view.openCamera();
    }

    @Override
    public void onButtonGalleryTouched() {
        view.openGallery();
    }

    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionError() {
    }

    @Override
    public void addPhoto(String photoUri) {
        items.set(selectedPosition,photoUri);
        view.notifyPhotoAdded(items);
        selectedPosition = -1;
    }
}
