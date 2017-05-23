package com.buildone.dulado.interactor;

import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.SearchObject;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Alessandro Pryds on 05/05/2017.
 */

public interface IProductInteractor {
    Observable<ArrayList<SearchObject>> getProducts();
    Observable<ProductObject> getProductById(int productId);
}
