package com.buildone.dulado.modules;

import com.buildone.dulado.interactor.IUserInteractor;
import com.buildone.logic.interactor.UserInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alessandro Pryds on 04/05/2017.
 */

@Module
public class UserRepositoryModule {

    public UserRepositoryModule() {
    }


    @Provides
    static IUserInteractor providesUserInteractor() {
        return new UserInteractor();
    }

}
