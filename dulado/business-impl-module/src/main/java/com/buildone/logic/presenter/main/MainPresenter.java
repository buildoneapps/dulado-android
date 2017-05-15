package com.buildone.logic.presenter.main;

import com.buildone.dulado.contracts.MainContract;
import com.buildone.dulado.event.OnScrollChangedEvent;
import com.buildone.dulado.model.LiveObject;
import com.buildone.rxbus.RxBus;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private final int someId;
    private MainContract.View view;
    private ArrayList<LiveObject> liveItems;
    private boolean inGridMode;
    private CompositeDisposable compositeDisposable;
    private boolean adButtonVisible;

    @Inject
    public MainPresenter(MainContract.View view, @Named("someId") int someId) {
        this.view = view;
        this.someId = someId;
        this.liveItems = new ArrayList<>();
        this.adButtonVisible = true;
    }

    @Override
    public void start() {
        initSubscriptions();
        view.initToolbar();
        view.initViewPager();
        view.showToastMessage(String.valueOf(someId));
        view.initLiveRecycler();

        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        view.populateLiveRecycler(liveItems);
    }

    @Override
    public void initSubscriptions() {
        if(compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(RxBus.getInstance().getEvents().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if(o instanceof OnScrollChangedEvent){
                    OnScrollChangedEvent event = (OnScrollChangedEvent) o;
                    onScrollChanged(event.isScrolling());
                }
            }
        }));
    }

    @Override
    public void disposeAll() {
        compositeDisposable.clear();
    }

    @Override
    public void onScrollChanged(boolean isScrolling) {
        if(isScrolling && adButtonVisible){
            view.hideCreateAdButton();
            adButtonVisible = false;
            return;
        }
        view.showCreateAdButton();
        adButtonVisible = true;
    }


    @Override
    public void onLiveItemTouched(int position) {
        view.navigateToStoreActivity(liveItems.get(position).getStoreId());
    }

    @Override
    public void onButtonMapTouched() {
        inGridMode = !inGridMode;
        view.setMapButtonVisible(false);
        view.setLiveRecyclerVisible(true);
        view.switchToMap(inGridMode);
    }



    @Override
    public void onButtonListTouched() {
        inGridMode = false;
        view.setMapButtonVisible(true);
        view.setLiveRecyclerVisible(false);
        view.switchToList(inGridMode);

        view.showCreateAdButton();
    }

    @Override
    public void onButtonGridTouched() {
        inGridMode = true;
        view.setMapButtonVisible(true);
        view.setLiveRecyclerVisible(false);
        view.switchToList(inGridMode);

        view.showCreateAdButton();
    }

    @Override
    public void onButtonSearchTouched() {
        view.navigateToSearchActivity();
    }

    @Override
    public void onButtonCreateAdTouched() {
        view.navigateToAddProductActivity();
    }

}
