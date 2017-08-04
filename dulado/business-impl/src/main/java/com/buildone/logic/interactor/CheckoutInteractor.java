package com.buildone.logic.interactor;


import com.buildone.dulado.interactor.ICheckoutInteractor;

import io.reactivex.Observable;

/**
 * Created by Alessandro Pryds on 07/06/2017.
 */

public class CheckoutInteractor implements ICheckoutInteractor {
    @Override
    public Observable<Double> calculateTaxOnlinePayment(float price) {
        return Observable.just(price-(price*0.08));
    }
}
