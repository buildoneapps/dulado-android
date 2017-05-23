package com.buildone.logic.presenter.main;

import com.buildone.dulado.contracts.MainListContract;
import com.buildone.dulado.event.OnListFormatModeChangedEvent;
import com.buildone.dulado.event.OnProductAddedEvent;
import com.buildone.dulado.event.OnScrollChangedEvent;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.SearchObject;
import com.buildone.rxbus.RxBus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class MainListFragPresenter implements MainListContract.Presenter {
    private IProductInteractor interactor;
    private MainListContract.View view;
    private CompositeDisposable disposable;
    private ArrayList<SearchObject> loadedProducts;

    @Inject
    public MainListFragPresenter(MainListContract.View view, IProductInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        this.loadedProducts = new ArrayList<>();
    }

    @Override
    public void start() {
        initSubscriptions();
        view.initListRecyclerView();
        view.initGridRecyclerView();
        loadProducts();
    }

    @Override
    public void loadProducts() {
        view.showLoading();
        disposable.add(interactor.getProducts()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<SearchObject>>() {
                    @Override
                    public void onNext(@NonNull ArrayList<SearchObject> searchObjects) {
                        loadedProducts = searchObjects;
                        view.populateGridRecyclerView(loadedProducts);
                        view.populateListRecyclerView(loadedProducts);
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
    public void initSubscriptions() {
        disposable = new CompositeDisposable();
        disposable.add(RxBus.getInstance().getEvents().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if (o instanceof OnListFormatModeChangedEvent) {
                    OnListFormatModeChangedEvent event = (OnListFormatModeChangedEvent) o;
                    switchListMode(event.getListFormat());
                }else if(o instanceof OnProductAddedEvent){
                    OnProductAddedEvent event = (OnProductAddedEvent) o;
                    loadedProducts.add(0,event.getProductAdded());

                    view.populateGridRecyclerView(loadedProducts);
                    view.populateListRecyclerView(loadedProducts);
                }
            }
        }));
    }

    @Override
    public void switchListMode(int listFormat) {
        if (loadedProducts.size() == 0) {
            view.showEmptyMessage();
            return;
        }
        switch (listFormat) {
            case 0:
                view.initListRecyclerView();
                view.populateListRecyclerView(loadedProducts);
                break;
            case 1:
                view.initGridRecyclerView();
                view.populateGridRecyclerView(loadedProducts);
                break;
        }
    }

    @Override
    public void onShow() {
        RxBus.getInstance().publish(new OnScrollChangedEvent(false));
    }

    @Override
    public void onHide() {
        RxBus.getInstance().publish(new OnScrollChangedEvent(true));
    }

    @Override
    public void disposeAll() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
