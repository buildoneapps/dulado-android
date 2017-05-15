package com.buildone.dulado.components.fragment;

import android.content.Context;

import com.buildone.dulado.contracts.MainMapContract;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.fragments.MainMapFragment;
import com.buildone.logic.presenter.main.MainMapFragPresenter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

@Subcomponent(modules = {
        FirebaseModule.class,
        MainMapFragmentSubComponent.MainMapFragmentModule.class
})
public interface MainMapFragmentSubComponent extends AndroidInjector<MainMapFragment> {

    @Module
    abstract class MainMapFragmentModule {
        @Provides
        @ForApplication
        static Context providesContext(MainMapFragment fragment) {
            return fragment.getActivity();
        }

        @Provides
        static GoogleApiClient providesGoogleApi(MainMapFragment fragment) {
            return new GoogleApiClient.Builder(fragment.getActivity())
                    .addConnectionCallbacks(fragment)
                    .addOnConnectionFailedListener(fragment)
                    .addApi(LocationServices.API)
                    .build();
        }

        @Binds
        abstract MainMapContract.View providesView(MainMapFragment fragment);

        @Provides
        static MainMapContract.Presenter providesPresenter(MainMapFragment fragment) {
            return new MainMapFragPresenter(fragment);
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainMapFragment> {
    }
}
