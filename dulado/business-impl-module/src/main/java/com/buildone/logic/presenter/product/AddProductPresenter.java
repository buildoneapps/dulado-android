package com.buildone.logic.presenter.product;

import com.buildone.dulado.contracts.AddProductContract;
import com.buildone.dulado.event.OnAddProductPhotoRequestEvent;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.rxbus.RxBus;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alessandro Pryds on 06/05/2017.
 */

public class AddProductPresenter implements AddProductContract.Presenter {
    private AddProductContract.View view;
    private IProductInteractor interactor;
    private CompositeDisposable disposable;

    public AddProductPresenter(AddProductContract.View view, IProductInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initToolbar();
        initSubscriptions();
    }

    @Override
    public void initSubscriptions() {
        disposable = new CompositeDisposable();
        disposable.add(RxBus.getInstance().getEvents().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if (o instanceof OnAddProductPhotoRequestEvent) {
                    OnAddProductPhotoRequestEvent event = (OnAddProductPhotoRequestEvent) o;
                    onButtonAddPhotoTouched(event.getPosition());
                }
            }
        }));
    }

    @Override
    public void disposeAll() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onButtonAddPhotoTouched(int position) {
        view.showPhotoChooserDialog();
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
}
