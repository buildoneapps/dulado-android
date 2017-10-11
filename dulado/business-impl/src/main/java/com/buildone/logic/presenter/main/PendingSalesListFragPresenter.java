package com.buildone.logic.presenter.main;

import com.buildone.dulado.contracts.PendingSalesContract;
import com.buildone.dulado.event.OnProductAddedEvent;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.PendingSaleObject;
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

public class PendingSalesListFragPresenter implements PendingSalesContract.Presenter {
    private IProductInteractor interactor;
    private PendingSalesContract.View view;
    private CompositeDisposable subscriptions;
    private ArrayList<SearchObject> loadedProducts;

    @Inject
    public PendingSalesListFragPresenter(PendingSalesContract.View view, IProductInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        this.loadedProducts = new ArrayList<>();
    }

    @Override
    public void start() {
        initSubscriptions();
        view.initListRecyclerView();
        loadProducts();
    }

    @Override
    public void loadProducts() {
        view.showLoading();
        subscriptions.add(interactor.getProducts()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<SearchObject>>() {
                    @Override
                    public void onNext(@NonNull ArrayList<SearchObject> searchObjects) {
                        loadedProducts = searchObjects;
                        ArrayList<PendingSaleObject> arrayList = new ArrayList<PendingSaleObject>();
                        arrayList.add(new PendingSaleObject());
                        view.populateListRecyclerView(arrayList);
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
        subscriptions = new CompositeDisposable();
        subscriptions.add(RxBus.getInstance().getEvents().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if(o instanceof OnProductAddedEvent){
                    OnProductAddedEvent event = (OnProductAddedEvent) o;
                    //loadedProducts.add(0,event.getProductAdded());

                    //view.populateListRecyclerView(loadedProducts);
                }
            }
        }));
    }

    @Override
    public void dispose() {
        if(!isDisposed()){
            subscriptions.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return subscriptions == null || subscriptions.isDisposed();
    }
}
