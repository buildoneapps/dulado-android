package com.buildone.dulado.interactor;

import io.reactivex.Observable;

/**
 * Created by Alessandro Pryds on 07/06/2017.
 */

public interface ICheckoutInteractor {
    Observable<Double> calculateTaxOnlinePayment(float price);
}
