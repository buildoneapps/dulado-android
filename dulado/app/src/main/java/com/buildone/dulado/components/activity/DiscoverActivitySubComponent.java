package com.buildone.dulado.components.activity;


import android.content.Context;

import com.buildone.dulado.contracts.DiscoverContract;
import com.buildone.dulado.interactor.IDiscoverInteractor;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.DiscoverActivity;
import com.buildone.logic.interactor.DiscoverInteractor;
import com.buildone.logic.presenter.DiscoverPresenter;

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
        DiscoverActivitySubComponent.DiscoverActivityModule.class
})
public interface DiscoverActivitySubComponent extends AndroidInjector<DiscoverActivity> {

    @Module
    abstract class DiscoverActivityModule {
        @Binds
        @ForApplication
        abstract Context providesContext(DiscoverActivity activity);

        @Binds
        abstract DiscoverContract.View providesView(DiscoverActivity activity);

        @Provides
        static IDiscoverInteractor providesDiscoveryInteractor() {
            return new DiscoverInteractor();
        }

        @Provides
        static DiscoverContract.Presenter providesPresenter(DiscoverActivity activity, IDiscoverInteractor interactor) {
            return new DiscoverPresenter(activity, interactor);
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<DiscoverActivity> {
    }
}