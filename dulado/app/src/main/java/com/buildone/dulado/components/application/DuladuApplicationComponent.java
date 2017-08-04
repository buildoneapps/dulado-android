package com.buildone.dulado.components.application;

import com.buildone.dulado.application.BindingModule;
import com.buildone.dulado.application.DuladuApplication;
import com.buildone.dulado.components.activity.AddProductActivitySubComponent;
import com.buildone.dulado.components.activity.CheckoutOverviewActivitySubComponent;
import com.buildone.dulado.components.activity.DiscoverActivitySubComponent;
import com.buildone.dulado.components.activity.EditProfileActivitySubComponent;
import com.buildone.dulado.components.activity.MainActivitySubComponent;
import com.buildone.dulado.components.activity.ProductActivitySubComponent;
import com.buildone.dulado.components.activity.SearchActivitySubComponent;
import com.buildone.dulado.components.activity.StoreActivitySubComponent;
import com.buildone.dulado.components.activity.UserProfileActivitySubComponent;
import com.buildone.dulado.components.fragment.MainListFragmentSubComponent;
import com.buildone.dulado.components.fragment.MainMapFragmentSubComponent;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                BindingModule.class,
                DuladuApplicationComponent.AppModule.class
        })
public interface DuladuApplicationComponent {

    void inject(DuladuApplication application);

    @Module(subcomponents = {
            MainActivitySubComponent.class,
            DiscoverActivitySubComponent.class,
            StoreActivitySubComponent.class,
            ProductActivitySubComponent.class,
            UserProfileActivitySubComponent.class,
            AddProductActivitySubComponent.class,
            EditProfileActivitySubComponent.class,
            MainMapFragmentSubComponent.class,
            MainListFragmentSubComponent.class,
            SearchActivitySubComponent.class,
            CheckoutOverviewActivitySubComponent.class
    })
    abstract class AppModule {
    }
}