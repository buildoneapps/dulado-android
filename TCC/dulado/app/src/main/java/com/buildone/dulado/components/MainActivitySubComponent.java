package com.buildone.dulado.components;


import android.content.Context;

import com.buildone.dulado.contracts.MainContract;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.main.MainActivity;
import com.buildone.logic.presenter.MainPresenter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

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
        MainActivitySubComponent.MainActivityModule.class
})
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {

    @Module
    abstract class MainActivityModule {
        @Binds
        @ForApplication
        abstract Context providesContext(MainActivity mainActivity);

        @Binds
        abstract MainContract.View providesMainView(MainActivity mainActivity);

        @Provides
        static GoogleApiClient providesGoogleApi(MainActivity mainActivity) {
            return new GoogleApiClient.Builder(mainActivity)
                    .addConnectionCallbacks(mainActivity)
                    .addOnConnectionFailedListener(mainActivity)
                    .addApi(LocationServices.API)
                    .build();
        }

        @Provides
        static MainContract.Presenter providesPresenter(MainActivity mainActivity, @Named("someId") int someId){
            return new MainPresenter(mainActivity,someId);
        }


        @Provides
        @Named("someId")
        static int provideSomeId(MainActivity mainActivity) {
            return mainActivity.getSomeId();
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}