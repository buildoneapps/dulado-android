package com.buildone.logic.presenter.store;

import com.buildone.dulado.contracts.StoreContract;
import com.buildone.dulado.interactor.IStoreInteractor;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.StoreObject;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 04/05/2017.
 */

public class StorePresenter implements StoreContract.Presenter {

    private StoreContract.View view;
    private IStoreInteractor interactor;
    private int storeId;

    private StoreObject store;
    private ArrayList<ProductObject> products;

    public StorePresenter(StoreContract.View view, IStoreInteractor interactor, int storeId) {
        this.view = view;
        this.interactor = interactor;
        this.storeId = storeId;
        this.products = new ArrayList<>();
    }

    @Override
    public void start() {
        view.initToolbar();
        products.add(new ProductObject(1,1,"teste",1.99f));

        store = new StoreObject(storeId,"ABC",1);
    }

    @Override
    public void onProductSelected(int position) {
        view.navigateToProductActivity(products.get(position));
    }

    @Override
    public void onButtonBookmarkTouched() {

    }

    @Override
    public void onPhoneButtonTouched() {

    }

    @Override
    public void onButtonProfileTouched() {
        view.navigateToUserProfile(store.getSellerId());
    }
}
