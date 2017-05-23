package com.buildone.dulado.ui.activity.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.AddProductContract;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.parcel.ProductParcel;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.buildone.dulado.ui.adapter.holder.AddProductPhotoHolder;
import com.buildone.dulado.utils.CameraIntentHelper;
import com.buildone.dulado.utils.CameraIntentHelperCallback;
import com.buildone.dulado.utils.RecyclerItemClickListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class AddProductActivity extends BaseActivity implements AddProductContract.View, CameraIntentHelperCallback {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    AddProductContract.Presenter presenter;

    @Inject
    CameraIntentHelper cameraHelper;

    @BindView(R.id.rvPhotos)
    RecyclerView rvPhotos;
    @BindView(R.id.cbFacebook)
    CheckBox cbFacebook;
    @BindView(R.id.cbInstagram)
    CheckBox cbInstagram;
    @BindView(R.id.cbTwitter)
    CheckBox cbTwitter;

    private FlexibleAdapter<AddProductPhotoHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        AndroidInjection.inject(this);
        presenter.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cameraHelper.onActivityResult(requestCode,resultCode,data);
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
    public void initPhotosRecyclerView(ArrayList<String> items) {
        List<AddProductPhotoHolder> holders = new ArrayList<>();
        adapter = new FlexibleAdapter<AddProductPhotoHolder>();
        for(int i = 0; i < items.size(); i++){
            holders.add(new AddProductPhotoHolder(this,items.get(i), i == 0));
        }
        adapter.addItems(holders);
        rvPhotos.setAdapter(adapter);
        rvPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvPhotos.setHasFixedSize(true);
        rvPhotos.addOnItemTouchListener(new RecyclerItemClickListener(this, rvPhotos, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.onButtonAddPhotoTouched(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void notifyPhotoAdded(ArrayList<String> items) {
        List<AddProductPhotoHolder> holders = new ArrayList<>();
        for(int i = 0; i < items.size(); i++){
            if(i == 0) {
                holders.add(new AddProductPhotoHolder(this, items.get(i), true));
            }else{
                holders.add(new AddProductPhotoHolder(this, items.get(i), !items.get(i-1).isEmpty()));
            }
        }
        adapter.setItems(holders);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showPhotoChooserDialog() {

    }

    @Override
    public void openCamera() {
        cameraHelper.startCameraIntent();
    }

    @Override
    public void openGallery() {

    }

    @Override
    public void navigateToProductPreview(ProductObject product) {

    }

    @Override
    public void navigateToProductPage(ProductObject product) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_PRODUCT_OBJECT, new ProductParcel(product));
        startActivity(intent);
    }

    @Override
    public void navigateToShareActivity(ProductObject product) {

    }


    //region Camera Intent Helper
    @Override
    public void onPhotoUriFound(Date dateCameraIntentStarted, Uri photoUri, int rotateXDegrees) {
        showToastMessage("Camera: addPhoto() - " + photoUri);
        presenter.addPhoto(photoUri.toString());

    }

    @Override
    public void deletePhotoWithUri(Uri photoUri) {
        showToastMessage("Camera: deletePhotoWithUri()");

    }

    @Override
    public void onSdCardNotMounted() {
        showToastMessage("Camera: onSdCardNotMounted()");

    }

    @Override
    public void onCanceled() {
        showToastMessage("Camera: onCanceled()");
    }

    @Override
    public void onCouldNotTakePhoto() {
        showToastMessage("Camera: onCouldNotTakePhoto()");
    }

    @Override
    public void onPhotoUriNotFound() {
        showToastMessage("Camera: onPhotoUriNotFound()");
    }

    @Override
    public void logException(Exception e) {

    }
    //endregion
}
