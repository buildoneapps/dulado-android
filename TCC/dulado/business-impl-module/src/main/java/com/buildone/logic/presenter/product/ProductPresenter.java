package com.buildone.logic.presenter.product;

import com.buildone.dulado.contracts.ProductContract;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.ProductObject;

import javax.inject.Named;

/**
 * Created by Alessandro Pryds on 05/05/2017.
 */

public class ProductPresenter implements ProductContract.Presenter {
    private ProductObject product;
    private ProductContract.View view;
    private IProductInteractor interactor;

    public ProductPresenter(ProductContract.View view, IProductInteractor interactor, @Named("Product") ProductObject product) {
        this.view = view;
        this.interactor = interactor;
        this.product = product;
    }

    @Override
    public void start() {
        view.initToolbar("","");
    }

    @Override
    public void onButtonCheckoutTouched() {
        view.navigateToCheckoutActivity(product);
    }

    @Override
    public void onButtonChatTouched() {
        view.navigateToChatActivity(product);
    }

    @Override
    public void onButtonAddWishListTouched() {

    }

    @Override
    public void onButtonRemindMeTouched() {

    }

    @Override
    public void onButtonStoreTouched() {
        view.navigateToStoreActivity(product.getStoreId());
    }

    @Override
    public void onIncreaseQuantity() {

    }

    @Override
    public void onDecreaseQuantity() {

    }
}
