package com.buildone.logic.presenter.main;

import com.buildone.dulado.contracts.PendingSalesContract;
import com.buildone.dulado.event.OnPendingSaleStateChangedEvent;
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
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class PendingSalesListFragPresenter implements PendingSalesContract.Presenter {
    private IProductInteractor interactor;
    private PendingSalesContract.View view;
    private CompositeDisposable subscriptions;
    private ArrayList<PendingSaleObject> pendingSales;

    @Inject
    public PendingSalesListFragPresenter(PendingSalesContract.View view, IProductInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        initSubscriptions();
        view.initListRecyclerView();
        pendingSales = new ArrayList<>();
        loadProducts();

    }

    @Override
    public void loadProducts() {
        view.showProgress();
        subscriptions.add(interactor.getProducts()
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<SearchObject>>() {
                    @Override
                    public void onNext(@NonNull ArrayList<SearchObject> searchObjects) {
                        pendingSales.add(PendingSaleObject.createTest());
                        view.populateListRecyclerView(pendingSales);
                        view.hideEmptyMessage();
                        view.hideProgress();
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
        subscriptions.add(RxBus.getInstance().getEvents().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Object>() {
                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof OnPendingSaleStateChangedEvent) {
                            OnPendingSaleStateChangedEvent event = (OnPendingSaleStateChangedEvent) o;
                            int index = pendingSales.indexOf(event.getPendingSale());
                            pendingSales.remove(index);
                            view.removeItem(index);

                            if (pendingSales.size() == 0) {
                                view.showEmptyMessage();
                            }
                        }
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
    public void refreshList() {
        loadProducts();
    }

    @Override
    public void dispose() {
        if (!isDisposed()) {
            subscriptions.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return subscriptions == null || subscriptions.isDisposed();
    }
}
