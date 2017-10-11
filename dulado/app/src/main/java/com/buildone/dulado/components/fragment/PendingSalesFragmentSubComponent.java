package com.buildone.dulado.components.fragment;

import android.content.Context;

import com.buildone.dulado.contracts.PendingSalesContract;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.fragments.PendingSalesListFragment;
import com.buildone.logic.interactor.ProductInteractor;
import com.buildone.logic.presenter.main.PendingSalesListFragPresenter;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

@Subcomponent(modules = {
        PendingSalesFragmentSubComponent.PendingSalesFragmentModule.class
})
public interface PendingSalesFragmentSubComponent extends AndroidInjector<PendingSalesListFragment> {

    @Module
    abstract class PendingSalesFragmentModule {
        @Provides
        @ForApplication
        static Context providesContext(PendingSalesListFragment fragment) {
            return fragment.getActivity();
        }

        @Provides
        static PendingSalesContract.Presenter providesPresenter(PendingSalesListFragment fragment, IProductInteractor interactor) {
            return new PendingSalesListFragPresenter(fragment, interactor);
        }

        @Provides
        static IProductInteractor providesProductIteractor() {
            return new ProductInteractor();
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PendingSalesListFragment> {
    }
}
