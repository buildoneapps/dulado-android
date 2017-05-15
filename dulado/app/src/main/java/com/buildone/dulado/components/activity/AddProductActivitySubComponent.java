package com.buildone.dulado.components.activity;


import android.content.Context;

import com.buildone.dulado.contracts.AddProductContract;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.modules.ProductRepositoryModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.product.AddProductActivity;
import com.buildone.logic.presenter.product.AddProductPresenter;

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
        ProductRepositoryModule.class,
        AddProductActivitySubComponent.AddProductActivityModule.class
})
public interface AddProductActivitySubComponent extends AndroidInjector<AddProductActivity> {

    @Module
    abstract class AddProductActivityModule {
        @Binds
        @ForApplication
        abstract Context providesContext(AddProductActivity activity);

        @Binds
        abstract AddProductContract.View providesView(AddProductActivity activity);

        @Provides
        static AddProductContract.Presenter providesPresenter(AddProductActivity activity, IProductInteractor interactor) {
            return new AddProductPresenter(activity, interactor);
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AddProductActivity> {
    }
}