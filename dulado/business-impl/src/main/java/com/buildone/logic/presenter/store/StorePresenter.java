package com.buildone.logic.presenter.store;

import com.buildone.dulado.contracts.StoreContract;
import com.buildone.dulado.interactor.IStoreInteractor;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.SellerObject;
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
        products.add(new ProductObject(1, storeId, 12.2f, "teste", "Teste descricao", new ArrayList<String>() {{
            add("https://s-media-cache-ak0.pinimg.com/736x/b1/2a/74/b12a74b433c345e8c77131c1681e7b83.jpg");
        }}, new SellerObject(1, "https://img.elo7.com.br/users/picture/186E8.jpg?59791763")));

        store = new StoreObject(storeId, "ABC", 1);
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
