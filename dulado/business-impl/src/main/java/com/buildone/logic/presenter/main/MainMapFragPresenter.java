package com.buildone.logic.presenter.main;

import com.buildone.dulado.contracts.MainMapContract;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.LiveObject;
import com.buildone.dulado.model.SearchObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class MainMapFragPresenter implements MainMapContract.Presenter {

    private static final int INITIAL_RADIUS = 1000;
    private MainMapContract.View view;
    private boolean permissionGranted;
    private boolean mapReady;
    private CompositeDisposable compositeDisposable;
    private ArrayList<SearchObject> storeItems;
    private ArrayList<LiveObject> liveItems;
    private IProductInteractor productInteractor;

    @Inject
    public MainMapFragPresenter(MainMapContract.View view, IProductInteractor productInteractor) {
        this.view = view;
        this.storeItems = new ArrayList<>();
        this.liveItems = new ArrayList<>();
        this.productInteractor = productInteractor;
    }
    @Override
    public void start() {
        initSubscriptions();

        view.initPermissions();
        view.initMap();
        view.initStoresScrollView();
    }

    @Override
    public void initSubscriptions() {
        if(compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        compositeDisposable.add(productInteractor.getProducts()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<SearchObject>>() {
                    @Override
                    public void onNext(@NonNull ArrayList<SearchObject> searchObjects) {
                        storeItems = searchObjects;
                        view.populateStoreScrollView(storeItems);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    @Override
    public void disposeAll() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }


    @Override
    public void onPermissionFailed() {
        view.showToastMessage("Permission Failed");

    }

    @Override
    public void onPermissionGranted(String[] receivedPermissions, List<String> required) {
        ArrayList<String> permissions = new ArrayList<String>();
        Collections.addAll(permissions, receivedPermissions);
        /*switch (pendingAction){
            case CAMERA:
                if(permissions.containsAll(cameraPermissions)){
                    pendingAction = NONE;
                    view.openCamera();
                }
                break;
            case GALLERY:
                pendingAction = NONE;
                if(permissions.containsAll(galleryPermissions)){
                    view.openGallery();
                }
                break;
            default:
                break;
        }*/
        if (permissions.containsAll(required)) {
            permissionGranted = true;
            if (mapReady) {
                view.enableMapInteraction();
            }
            //view.showToastMessage("Permission Granted");
            view.initLocationService();
            return;
        }
        view.showToastMessage("Permission Error");
    }

    public void onMapReady() {
        mapReady = true;
        view.loadMapStyle();

        if (permissionGranted) {
            view.enableMapInteraction();
        }
    }

    @Override
    public void onGoogleConnected() {
        view.centerUser();
        view.showRadius(INITIAL_RADIUS);
    }

    @Override
    public void onButtonHideStoresTouched() {
        view.hideStoresScrollView();
    }

    @Override
    public void onCameraMove() {
        view.hideStoresScrollView();
    }

}
