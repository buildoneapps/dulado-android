package com.buildone.dulado.components.activity;


import android.content.Context;

import com.buildone.dulado.contracts.EditProfileContract;
import com.buildone.dulado.interactor.IUserInteractor;
import com.buildone.dulado.modules.FirebaseModule;
import com.buildone.dulado.modules.UserRepositoryModule;
import com.buildone.dulado.scope.ForApplication;
import com.buildone.dulado.ui.activity.EditProfileActivity;
import com.buildone.logic.presenter.EditProfilePresenter;

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
        UserRepositoryModule.class,
        EditProfileActivitySubComponent.EditProfileActivityModule.class
})
public interface EditProfileActivitySubComponent extends AndroidInjector<EditProfileActivity> {

    @Module
    abstract class EditProfileActivityModule {
        @Binds
        @ForApplication
        abstract Context providesContext(EditProfileActivity activity);

        @Binds
        abstract EditProfileContract.View providesView(EditProfileActivity activity);

        @Provides
        static EditProfileContract.Presenter providesPresenter(EditProfileActivity activity, IUserInteractor interactor) {
            return new EditProfilePresenter(activity, interactor);
        }

    }

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<EditProfileActivity> {
    }
}