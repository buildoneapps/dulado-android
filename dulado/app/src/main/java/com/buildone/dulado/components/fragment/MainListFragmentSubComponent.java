package com.buildone.dulado.components.fragment;

import android.content.Context;

import com.buildone.dulado.contracts.MainListContract;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.fragments.MainListFragment;
import com.buildone.logic.interactor.ProductInteractor;
import com.buildone.logic.presenter.main.MainListFragPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

@Subcomponent(modules = {
        FirebaseModule.class,
        MainListFragmentSubComponent.MainListFragmentModule.class
})
public interface MainListFragmentSubComponent extends AndroidInjector<MainListFragment> {

    @Module
    abstract class MainListFragmentModule {
        @Provides
        @ForApplication
        static Context providesContext(MainListFragment fragment) {
            return fragment.getActivity();
        }

        @Provides
        static MainListContract.Presenter providesPresenter(MainListFragment fragment, IProductInteractor interactor) {
            return new MainListFragPresenter(fragment,interactor);
        }

        @Provides
        static IProductInteractor providesProductIteractor() {
            return new ProductInteractor();
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainListFragment> {
    }
}
