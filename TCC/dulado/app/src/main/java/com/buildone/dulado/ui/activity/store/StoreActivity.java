package com.buildone.dulado.ui.activity.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.StoreContract;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.parcel.ProductParcel;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.buildone.dulado.ui.activity.UserProfileActivity;
import com.buildone.dulado.ui.activity.product.ProductActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class StoreActivity extends BaseActivity implements StoreContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    StoreContract.Presenter presenter;

    private int storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        this.storeId = getIntent().getExtras().getInt(AppConstants.INTENT_TAG_STORE_ID, -1);

        AndroidInjection.inject(this);
        presenter.start();
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initProductsRecyclerView() {

    }

    @Override
    public void populateProductsRecyclerView(ArrayList<ProductObject> items) {

    }

    @Override
    public void loadMap(double latitude, double longitude) {

    }

    @Override
    public void setStoreOnwerName(String name) {

    }

    @Override
    public void setStoreOwnerCity(String city) {

    }

    @Override
    public void loadStoreOwnerPhoto(String url) {

    }

    @Override
    public void navigateToProductActivity(ProductObject product) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_PRODUCT_OBJECT, new ProductParcel(product));
        startActivity(intent);
    }

    @Override
    public void navigateToUserProfile(int sellerId) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_USER_PROFILE_ID, sellerId);
        startActivity(intent);
    }

    @Override
    public int getStoreId() {
        return storeId;
    }

    @OnClick({R.id.btnUserProfile, R.id.btnViewProduct})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnUserProfile:
                presenter.onButtonProfileTouched();
                break;
            case R.id.btnViewProduct:
                presenter.onProductSelected(0);
                break;
        }
    }
}
