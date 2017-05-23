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
        view.initToolbar("", "");
        initSubscriptions();
        if(product.getId() > 0){
            setupProduct(product);
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
        view.setProductDescription(product.getDescription());
        view.setProductName(product.getName());
        view.setProductPrice("R$ " + product.getPrice());
        view.setProductQuantity(0);
        view.setProductTags(new ArrayList<String>());
        view.setProductWish(false);
    }

    @Override
    public void disposeAll() {
        if(subscriptions != null && !subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
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
