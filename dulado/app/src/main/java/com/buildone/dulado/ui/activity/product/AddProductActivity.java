package com.buildone.dulado.ui.activity.product;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.buildone.dulado.R;
import com.buildone.dulado.contracts.AddProductContract;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AddProductActivity extends BaseActivity implements AddProductContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    AddProductContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

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
    public void checkPermissions() {

    }

    @Override
    public void initPhotosRecyclerView() {

    }

    @Override
    public void showPhotoChooserDialog() {

    }

    @Override
    public void openCamera() {

    }

    @Override
    public void openGallery() {

    }

    @Override
    public void navigateToProductPreview(ProductObject product) {

    }

    @Override
    public void navigateToProductPage(ProductObject product) {

    }

    @Override
    public void navigateToShareActivity(ProductObject product) {

    }
}
