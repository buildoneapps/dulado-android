package com.buildone.dulado.components;

import com.buildone.dulado.application.BindingModule;
import com.buildone.dulado.application.DuladuApplication;

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
                MyApplicationComponent.AppModule.class
        })
public interface MyApplicationComponent {

    void inject(DuladuApplication application);

    @Module(subcomponents = {
            MainActivitySubComponent.class,
            DiscoverActivitySubComponent.class,
            StoreActivitySubComponent.class,
            ProductActivitySubComponent.class,
            UserProfileActivitySubComponent.class,
            AddProductActivitySubComponent.class,
            EditProfileActivitySubComponent.class
    })
    abstract class AppModule {
    }
}