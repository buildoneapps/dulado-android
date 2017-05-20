package com.buildone.logic.presenter.main;

import com.buildone.dulado.contracts.MainContract;
import com.buildone.dulado.event.OnScrollChangedEvent;
import com.buildone.dulado.interactor.IMainInteractor;
import com.buildone.dulado.model.LiveObject;
import com.buildone.rxbus.RxBus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private IMainInteractor interactor;
    private MainContract.View view;

    private int someId;
    private boolean inGridMode;
    private boolean adButtonVisible;
    private ArrayList<LiveObject> liveItems;

    private CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(MainContract.View view, IMainInteractor interactor, @Named("someId") int someId) {
        this.view = view;
        this.interactor = interactor;
        this.someId = someId;
        this.liveItems = new ArrayList<>();
        this.adButtonVisible = false;
    }

    @Override
    public void start() {
        view.initToolbar();
        view.initViewPager();
        view.showToastMessage(String.valueOf(someId));
        view.initLiveRecycler();
        view.hideCreateAdButton();
        initSubscriptions();
    }

    @Override
    public void initSubscriptions() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(RxBus.getInstance().getEvents().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if (o instanceof OnScrollChangedEvent) {
                    OnScrollChangedEvent event = (OnScrollChangedEvent) o;
                    onScrollChanged(event.isScrolling());
                }
            }
        }));
        compositeDisposable
                .add(interactor.getLiveByPosition(0, 0)
                        .delay(4, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(
                                new DisposableObserver<ArrayList<LiveObject>>() {
                                    @Override
                                    public void onNext(@NonNull ArrayList<LiveObject> liveObjects) {
                                        liveItems = liveObjects;
                                        view.populateLiveRecycler(liveItems);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                }
                        )
                );
    }

    @Override
    public void disposeAll() {
        compositeDisposable.clear();
    }

    @Override
    public void onScrollChanged(boolean isScrolling) {
        if (isScrolling && adButtonVisible) {
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
        view.hideCreateAdButton();
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
