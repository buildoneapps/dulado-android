package com.buildone.dulado.modules;

import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.logic.interactor.ProductInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alessandro Pryds on 04/05/2017.
 */

@Module
public class ProductRepositoryModule {

    public ProductRepositoryModule() {
    }


    @Provides
    static IProductInteractor providesProductInteractor() {
        return new ProductInteractor();
    }

}
