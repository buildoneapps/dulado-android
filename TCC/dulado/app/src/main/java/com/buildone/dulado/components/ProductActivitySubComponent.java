package com.buildone.dulado.components;


import android.content.Context;

import com.buildone.dulado.contracts.ProductContract;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.product.ProductActivity;
import com.buildone.logic.interactor.ProductInteractor;
import com.buildone.logic.presenter.product.ProductPresenter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */
@Subcomponent(modules = {
        FirebaseModule.class,
        ProductActivitySubComponent.ProductActivityModule.class
})
public interface ProductActivitySubComponent extends AndroidInjector<ProductActivity> {

    @Module
    abstract class ProductActivityModule {
        @Binds
        @ForApplication
        abstract Context providesContext(ProductActivity activity);

        @Binds
        abstract ProductContract.View providesView(ProductActivity activity);


        @Provides
        static ProductContract.Presenter providesPresenter(ProductActivity activity, IProductInteractor interactor, @Named("productObject") ProductObject product) {
            return new ProductPresenter(activity, interactor, product);
        }

        @Provides
        static IProductInteractor providesProductInteractor() {
            return new ProductInteractor();
        }

        @Provides
        @Named("productObject")
        static ProductObject provideProductObject(ProductActivity activity) {
            return activity.getProduct();
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProductActivity> {
    }
}