package com.buildone.dulado.components;


import android.content.Context;

import com.buildone.dulado.contracts.UserProfileContract;
import com.buildone.dulado.interactor.IUserInteractor;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.modules.UserRepositoryModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.UserProfileActivity;
import com.buildone.logic.presenter.UserProfilePresenter;

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
        UserRepositoryModule.class,
        FirebaseModule.class,
        UserProfileActivitySubComponent.UserProfileActivityModule.class
})
public interface UserProfileActivitySubComponent extends AndroidInjector<UserProfileActivity> {

    @Module
    abstract class UserProfileActivityModule {

        @Binds
        @ForApplication
        abstract Context providesContext(UserProfileActivity activity);

        @Binds
        abstract UserProfileContract.View providesView(UserProfileActivity activity);

        @Provides
        static UserProfileContract.Presenter providesPresenter(UserProfileActivity activity, IUserInteractor interactor, @Named("profileUserId") int profileUserId) {
            return new UserProfilePresenter(activity, interactor, profileUserId);
        }

        @Named("profileUserId")
        @Provides
        static int providesUserId(UserProfileActivity activity){
            return activity.getUserId();
        }
    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserProfileActivity> {
    }
}