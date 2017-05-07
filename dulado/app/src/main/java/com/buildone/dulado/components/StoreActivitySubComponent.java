package com.buildone.dulado.components;


import android.content.Context;

import com.buildone.dulado.contracts.StoreContract;
import com.buildone.dulado.interactor.IStoreInteractor;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.store.StoreActivity;
import com.buildone.logic.interactor.StoreInteractor;
import com.buildone.logic.presenter.store.StorePresenter;

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
        StoreActivitySubComponent.StoreActivityModule.class
})
public interface StoreActivitySubComponent extends AndroidInjector<StoreActivity> {

    @Module
    abstract class StoreActivityModule {
        @Binds
        @ForApplication
        abstract Context providesContext(StoreActivity activity);

        @Binds
        abstract StoreContract.View providesView(StoreActivity activity);


        @Provides
        static StoreContract.Presenter providesPresenter(StoreActivity activity, IStoreInteractor interactor, @Named("storeId") int storeId) {
            return new StorePresenter(activity, interactor, storeId);
        }

        @Provides
        static IStoreInteractor providesStoreInteractor() {
            return new StoreInteractor();
        }

        @Provides
        @Named("storeId")
        static int provideStoreId(StoreActivity activity) {
            return activity.getStoreId();
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<StoreActivity> {
    }
}