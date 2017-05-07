package com.buildone.logic.presenter;

import com.buildone.dulado.contracts.MainContract;
import com.buildone.dulado.event.OnProductTouchedEvent;
import com.buildone.dulado.event.OnStoreTouchedEvent;
import com.buildone.dulado.model.LiveObject;
import com.buildone.dulado.model.StoreObject;
import com.buildone.rxbus.RxBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final int INITIAL_RADIUS = 1000;
    private final int someId;
    private MainContract.View view;
    private boolean permissionGranted;
    private boolean mapReady;
    private CompositeDisposable compositeDisposable;
    private ArrayList<StoreObject> storeItems;
    private ArrayList<LiveObject> liveItems;

    @Inject
    public MainPresenter(MainContract.View view, @Named("someId") int someId) {
        this.view = view;
        this.someId = someId;
        this.storeItems = new ArrayList<>();
        this.liveItems = new ArrayList<>();
    }

    @Override
    public void start() {
        initSubscriptions();

        view.initToolbar();
        view.initPermissions();
        view.initMap();
        view.showToastMessage(String.valueOf(someId));
        view.initLiveRecycler();
        view.initStoresScrollView();

        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        view.populateLiveRecycler(liveItems);

        storeItems.add(new StoreObject(0, "https://github.com/bumptech/glide/raw/master/static/glide_logo.png", 0));
        storeItems.add(new StoreObject(0, "https://github.com/bumptech/glide/raw/master/static/glide_logo.png", 0));
        storeItems.add(new StoreObject(0, "https://github.com/bumptech/glide/raw/master/static/glide_logo.png", 0));
        storeItems.add(new StoreObject(0, "https://github.com/bumptech/glide/raw/master/static/glide_logo.png", 0));
        storeItems.add(new StoreObject(0, "https://github.com/bumptech/glide/raw/master/static/glide_logo.png", 0));
        view.populateStoreScrollView(storeItems);
    }

    @Override
    public void initSubscriptions() {
        if(compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(RxBus.getInstance().getEvents().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if(o instanceof OnStoreTouchedEvent){
                    OnStoreTouchedEvent event = (OnStoreTouchedEvent) o;
                    view.navigateToStoreActivity(event.getStore().getId());
                }else if(o instanceof OnProductTouchedEvent){
                    OnProductTouchedEvent event = (OnProductTouchedEvent) o;
                    view.navigateToProductActivity(event.getProduct());
                }
            }
        }));
    }

    @Override
    public void unsubscribeAll() {
        compositeDisposable.clear();
    }

    @Override
    public void onSearchTouched() {
        view.navigateToSearchActivity();
    }

    @Override
    public void onStoreSelected(int position) {
        view.navigateToStoreActivity(storeItems.get(position).getId());
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
            view.showToastMessage("Permission Granted");
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

    @Override
    public void onLiveItemTouched(int position) {
        view.navigateToStoreActivity(liveItems.get(position).getStoreId());
    }

}
