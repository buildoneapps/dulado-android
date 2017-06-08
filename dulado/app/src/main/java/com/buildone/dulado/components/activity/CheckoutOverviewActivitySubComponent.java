package com.buildone.dulado.components.activity;


import android.app.Activity;
import android.content.Context;

import com.buildone.dulado.contracts.CheckoutOverviewContract;
import com.buildone.dulado.interactor.ICheckoutInteractor;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.modules.CheckoutRepositoryModule;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.checkout.CheckoutOverviewActivity;
import com.buildone.logic.presenter.checkout.CheckoutOverviewPresenter;

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
        CheckoutRepositoryModule.class,
        CheckoutOverviewActivitySubComponent.CheckoutOverviewActivityModule.class
})
public interface CheckoutOverviewActivitySubComponent extends AndroidInjector<CheckoutOverviewActivity> {

    @Module
    abstract class CheckoutOverviewActivityModule {
        @Binds
        @ForApplication
        abstract Context providesContext(CheckoutOverviewActivity activity);

        @Binds
        @ForApplication
        abstract Activity providesActivity(CheckoutOverviewActivity activity);

        @Binds
        abstract CheckoutOverviewContract.View providesView(CheckoutOverviewActivity activity);


        @Provides
        static CheckoutOverviewContract.Presenter providesPresenter(CheckoutOverviewContract.View view, ICheckoutInteractor interactor, @Named("productObject") ProductObject product) {
            return new CheckoutOverviewPresenter(view, interactor, product);
        }

        @Provides
        @Named("productObject")
        static ProductObject provideProductObject(CheckoutOverviewActivity activity) {
            return activity.getProductParcel().getProduct();
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CheckoutOverviewActivity> {
    }
}