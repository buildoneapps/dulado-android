package com.buildone.dulado.components.activity;


import android.content.Context;

import com.buildone.dulado.contracts.SearchContract;
import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.main.SearchActivity;
import com.buildone.logic.interactor.ProductInteractor;
import com.buildone.logic.presenter.product.SearchPresenter;

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
        SearchActivitySubComponent.SearchActivityModule.class
})
public interface SearchActivitySubComponent extends AndroidInjector<SearchActivity> {

    @Module
    abstract class SearchActivityModule {
        @Binds
        @ForApplication
        abstract Context providesContext(SearchActivity activity);

        @Binds
        abstract SearchContract.View providesView(SearchActivity activity);

        @Provides
        static SearchContract.Presenter providesPresenter(SearchContract.View view, IProductInteractor interactor) {
            return new SearchPresenter(view, interactor);
        }

        @Provides
        static IProductInteractor providesInteractor() {
            return new ProductInteractor();
        }
    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchActivity> {
    }
}