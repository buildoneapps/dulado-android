package com.buildone.logic.presenter.checkout;

import com.buildone.dulado.contracts.CheckoutOverviewContract;
import com.buildone.dulado.interactor.ICheckoutInteractor;
import com.buildone.dulado.model.ProductObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alessandro Pryds on 07/06/2017.
 */

public class CheckoutOverviewPresenter implements CheckoutOverviewContract.Presenter {

    private ProductObject product;
    private CheckoutOverviewContract.View view;
    private ICheckoutInteractor interactor;
    private int cartQuantity;
    private int shippingMethod;
    private int selectedPaymentMethod;
    private int currentProgress;
    private boolean useCupon;
    private float shippingPrice;
    private float discount;
    private int discountPercent;

    public CheckoutOverviewPresenter(CheckoutOverviewContract.View view, ICheckoutInteractor interactor, ProductObject product) {
        this.view = view;
        this.interactor = interactor;
        this.product = product;
        this.cartQuantity = 1;
        this.shippingMethod = -1;
        this.selectedPaymentMethod = -1;
        this.currentProgress = 0;
    }

    @Override
    public void start() {
        view.initToolbar();
        view.uncheckAllPaymentMethods();
        view.setProductName(product.getName());
        view.setProductTag(product.getTags());
        if(product.getProductImages() != null && product.getProductImages().size() > 0) {
            view.setProductPhoto(product.getProductImages().get(0));
        }
        view.loadMap();
        view.setQuantity(cartQuantity);
        updatePrice();
    }

    @Override
    public void selectPickup() {
        shippingMethod = 1;
        view.setDeliverySelected(false);
        view.setPickupSelected(true);
    }

    @Override
    public void selectDelivery() {
        shippingMethod = 0;
        view.setDeliverySelected(true);
        view.setPickupSelected(false);
    }

    @Override
    public void selectPaymentMethod(int methodId) {
        view.uncheckAllPaymentMethods();
        selectedPaymentMethod = methodId;
        view.setPaymentSelectedStyle(methodId);
    }

    @Override
    public void confirmOrder() {
        view.blockShippingButtons();
        view.setConfirmOrderProgressVisible(true);
        if(!useCupon){
            view.hideCouponContainer();
        }
        nextStep();
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(@NonNull Long time) {
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.setConfirmOrderProgressVisible(false);
                        view.showPaymentSelected();
                        Observable.timer(4, TimeUnit.SECONDS)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread()) .subscribeWith(new DisposableObserver<Long>() {
                            @Override
                            public void onNext(@NonNull Long aLong) {

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                nextStep();
                            }
                        });
                    }
                });
    }

    @Override
    public void nextStep() {
        view.closeStatus(currentProgress);
        currentProgress++;
        view.openStatus(currentProgress);
    }

    @Override
    public void increaseQuantity() {
        if (cartQuantity < product.getStoreQuantity()) {
            cartQuantity++;
            view.setQuantity(cartQuantity);
            if (cartQuantity == product.getStoreQuantity()) {
                view.setIncreaseButtonEnabled(false);
            }
            view.setDecreaseButtonEnabled(true);
        }
        updatePrice();
    }

    private void updatePrice() {
        float subTotal = product.getPrice() * cartQuantity;
        discount = subTotal * discountPercent/100;
        view.setTotal(subTotal + shippingPrice - discount);
        view.setSubtotal(subTotal);
        view.setDiscount(discount);
        view.setShippingPrice(shippingPrice);
    }

    @Override
    public void decreaseQuantity() {
        if (cartQuantity > 1) {
            cartQuantity--;
            view.setQuantity(cartQuantity);
            if (cartQuantity == 1) {
                view.setDecreaseButtonEnabled(false);
            }
            view.setIncreaseButtonEnabled(true);
        }
        updatePrice();
    }

    @Override
    public void insertCupon() {
        useCupon = true;
        view.showCouponEditText();
    }

    @Override
    public void reviewProduct() {

    }

    @Override
    public void attemptToPurchase() {
        view.showSecurePaymentDialog();
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.hideSecurePaymentDialog();
                        nextStep();
                    }
                });
    }

    @Override
    public void attemptToApplyCoupon() {
        view.showCouponProgress();
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        discountPercent = 10;
                        updatePrice();
                        view.showCouponConfirmedIcon();
                        view.hideCouponProgress();
                    }
                });
    }
}
