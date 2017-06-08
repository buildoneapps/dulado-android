package com.buildone.dulado.modules;

import com.buildone.dulado.interactor.ICheckoutInteractor;
import com.buildone.logic.interactor.CheckoutInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alessandro Pryds on 04/05/2017.
 */

@Module
public class CheckoutRepositoryModule {

    public CheckoutRepositoryModule() {
    }


    @Provides
    static ICheckoutInteractor providesCheckoutInteractor() {
        return new CheckoutInteractor();
    }

}
