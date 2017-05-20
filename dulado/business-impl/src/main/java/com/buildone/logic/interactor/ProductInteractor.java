package com.buildone.logic.interactor;

import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.model.SellerObject;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Alessandro Pryds on 05/05/2017.
 */

public class ProductInteractor implements IProductInteractor {
    @Override
    public Observable<ArrayList<SearchObject>> getProducts() {
        ArrayList<SearchObject> objects = new ArrayList<>();
        objects.add(new SearchObject(0,"",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"",new SellerObject(),0.25f,"doce"));
        return Observable.fromArray(objects);
    }
}
