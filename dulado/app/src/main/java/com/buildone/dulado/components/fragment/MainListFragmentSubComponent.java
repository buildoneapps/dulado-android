package com.buildone.dulado.components.fragment;

import android.content.Context;

import com.buildone.dulado.contracts.MainListContract;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.model.SellerObject;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.fragments.MainListFragment;
import com.buildone.logic.presenter.main.MainListFragPresenter;

import java.util.ArrayList;

import javax.inject.Named;

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
        static MainListContract.Presenter providesPresenter(MainListFragment fragment, @Named("searchItems") ArrayList<SearchObject> items) {
            return new MainListFragPresenter(fragment, items);
        }

        @Named("searchItems")
        @Provides
        static ArrayList<SearchObject> providesItems(MainListFragment fragment) {
            ArrayList<SearchObject> items = new ArrayList<>();
            items.add(new SearchObject(0, "", new SellerObject(), 0, ""));
            items.add(new SearchObject(0, "", new SellerObject(), 0, ""));
            items.add(new SearchObject(0, "", new SellerObject(), 0, ""));
            items.add(new SearchObject(0, "", new SellerObject(), 0, ""));
            items.add(new SearchObject(0, "", new SellerObject(), 0, ""));
            return items;
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainListFragment> {
    }
}
