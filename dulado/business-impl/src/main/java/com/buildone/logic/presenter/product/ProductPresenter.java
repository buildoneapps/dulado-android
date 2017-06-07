package com.buildone.logic.presenter.product;

import com.buildone.dulado.contracts.ProductContract;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.SearchObject;

import java.util.ArrayList;

import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alessandro Pryds on 05/05/2017.
 */

public class ProductPresenter implements ProductContract.Presenter {
    private SearchObject productSearch;
    private ProductObject product;
    private ProductContract.View view;
    private IProductInteractor productInteractor;
    private CompositeDisposable subscriptions;

    public ProductPresenter(ProductContract.View view, IProductInteractor productInteractor, @Named("Product") ProductObject product, SearchObject productSearch) {
        this.view = view;
        this.productInteractor = productInteractor;
        this.product = product;
        this.productSearch = productSearch;
    }

    @Override
    public void start() {
        initSubscriptions();
        if(product.getId() > 0){
            setupProduct(product);
        }else if(productSearch.getId() > 0){
            setupProduct(new ProductObject(productSearch.getId(),
                    productSearch.getSeller().getId(),
                    productSearch.getPrice(),
                    productSearch.getProductName(),
                    "",
                    new ArrayList<String>(){{add(productSearch.getImageUrl());}},
                    productSearch.getSeller()));
        }
    }

    @Override
    public void initSubscriptions() {
        subscriptions = new CompositeDisposable();
        if (productSearch.getId() > 0) {
            subscriptions.add(productInteractor.getProductById(productSearch.getId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribeWith(new DisposableObserver<ProductObject>() {
                        @Override
                        public void onNext(@NonNull ProductObject productObject) {
                            product = productObject;
                            setupProduct(product);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    }));
        }
    }

    @Override
    public void setupProduct(ProductObject product) {
        view.initToolbar(product.getName(), "");
        view.setProductDescription(product.getDescription());
        view.setProductName(product.getName());
        view.setProductPrice("R$ " + product.getPrice());
        view.setProductQuantity(0);
        view.setProductTags(new ArrayList<String>());
        view.setProductWish(false);
        //view.setSellerName(product.getSeller().getName());
        view.setSellerPhoto(product.getSeller().getPhotoUrl());
        view.loadProductImages(product.getProductImages());
    }

    @Override
    public void disposeAll() {
        if(subscriptions != null && !subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }

    @Override
    public void goToCheckout() {
        view.navigateToCheckoutActivity(product);
    }

    @Override
    public void openChat() {
        view.navigateToChatActivity(product);
    }

    @Override
    public void addToWishList() {

    }

    @Override
    public void remindMe() {

    }

    @Override
    public void goToStore() {
        view.navigateToStoreActivity(product.getStoreId());
    }

}
