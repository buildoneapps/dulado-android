package com.buildone.logic.presenter.main;

import com.buildone.dulado.contracts.MainListContract;
import com.buildone.dulado.event.OnChatButtonTouchedEvent;
import com.buildone.dulado.event.OnListFormatModeChangedEvent;
import com.buildone.dulado.event.OnProductTouchedEvent;
import com.buildone.dulado.event.OnScrollChangedEvent;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.SearchObject;
import com.buildone.rxbus.RxBus;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class MainListFragPresenter implements MainListContract.Presenter {
    private MainListContract.View view;
    private ArrayList<SearchObject> items;
    private CompositeDisposable disposable;

    @Inject
    public MainListFragPresenter(MainListContract.View view, @Named("searchItems") ArrayList<SearchObject> items) {
        this.view = view;
        this.items = items;
    }

    @Override
    public void start() {
        initSubscriptions();
        view.initListRecyclerView();
        view.populateListRecyclerView(items);
    }

    @Override
    public void initSubscriptions() {
        disposable = new CompositeDisposable();
        disposable.add(RxBus.getInstance().getEvents().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if(o instanceof OnProductTouchedEvent){
                    OnProductTouchedEvent event = (OnProductTouchedEvent) o;
                    onProductSelected(event.getProduct());
                }else if(o instanceof OnChatButtonTouchedEvent){
                    OnChatButtonTouchedEvent event = (OnChatButtonTouchedEvent) o;
                    onChatProductTouched(event.getSearchProduct());
                }else if(o instanceof OnListFormatModeChangedEvent){
                    OnListFormatModeChangedEvent event = (OnListFormatModeChangedEvent) o;
                    switchListMode(event.getListFormat());
                }
            }
        }));
    }

    @Override
    public void switchListMode(int listFormat) {
        switch (listFormat){
            case 0:
                view.initListRecyclerView();
                view.populateListRecyclerView(items);
                break;
            case 1:
                view.initGridRecyclerView();
                view.populateGridRecyclerView(items);
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
    public void unsubscribeAll() {
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void onProductSelected(ProductObject product) {
        view.navigateToProductActivity(product);
    }

    @Override
    public void onChatProductTouched(SearchObject searchProduct) {
        view.navigateToChatActivity(searchProduct.getId());
    }
}
