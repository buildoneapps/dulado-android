package com.buildone.dulado.ui.activity.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.AddProductContract;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.model.SellerObject;
import com.buildone.dulado.parcel.ProductParcel;
import com.buildone.dulado.parcel.ProductSearchParcel;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.buildone.dulado.ui.adapter.holder.AddProductPhotoHolder;
import com.buildone.dulado.utils.CameraIntentHelper;
import com.buildone.dulado.utils.CameraIntentHelperCallback;
import com.buildone.dulado.utils.RecyclerItemClickListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
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
    @BindView(R.id.etProductName)
    EditText etProductName;
    @BindView(R.id.etProductDesc)
    EditText etProductDesc;
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.etTags)
    EditText etTags;
    @BindView(R.id.cbPickup)
    CheckBox cbPickup;
    @BindView(R.id.cbDelivery)
    CheckBox cbDelivery;
    @BindView(R.id.cbOnlinePayment)
    CheckBox cbOnlinePayment;
    @BindView(R.id.loadingPaymentCalc)
    ProgressBar loadingPaymentCalc;
    @BindView(R.id.tvOnlinePaymentIncome)
    TextView tvOnlinePaymentIncome;

    private FlexibleAdapter<AddProductPhotoHolder> adapter;
    private String photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            photoUri = getIntent().getExtras().getString(AppConstants.INTENT_TAG_PHOTO_URI);
        }
        AndroidInjection.inject(this);
        presenter.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cameraHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initPhotosRecyclerView(ArrayList<String> items) {
        List<AddProductPhotoHolder> holders = new ArrayList<>();
        adapter = new FlexibleAdapter<AddProductPhotoHolder>();
        for (int i = 0; i < items.size(); i++) {
            holders.add(new AddProductPhotoHolder(this, items.get(i), i == 1));
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
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                holders.add(new AddProductPhotoHolder(this, items.get(i), true));
            } else {
                holders.add(new AddProductPhotoHolder(this, items.get(i), !items.get(i - 1).isEmpty()));
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

    @Override
    public String getPhotoUri() {
        return photoUri;
    }

    @Override
    public void saveProductInApplication() {
        float price = 0;
        if(!etPrice.getText().toString().isEmpty()){
            price = Float.parseFloat(etPrice.getText().toString());
        }
        Intent extras = new Intent();
        extras.putExtra(AppConstants.INTENT_TAG_PRODUCT_SEARCH_OBJECT, new ProductSearchParcel(new SearchObject(0, etProductName.getText().toString(), photoUri, new SellerObject(0, "https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"),price,etTags.getText().toString())));
        setResult(RESULT_OK, extras);
        finish();
    }

    @Override
    public void hideOnlinePaymentLoading() {
        loadingPaymentCalc.setVisibility(View.GONE);
    }

    @Override
    public void showOnlinePurchaseLoading() {
        loadingPaymentCalc.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOnlinePaymentCalculatedValue() {
        tvOnlinePaymentIncome.setVisibility(View.GONE);
    }

    @Override
    public void showOnlinePaymentCalcultedValue(Double finalPrice) {
        tvOnlinePaymentIncome.setVisibility(View.VISIBLE);
        tvOnlinePaymentIncome.setText(String.format(getString(R.string.placeholder_online_payment_value),String.format(Locale.getDefault(),"%.2f",finalPrice)));
    }

    @Override
    public void showPriceNullError() {
        Toast.makeText(this, getString(R.string.error_price_null), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uncheckOnlinePayment() {
        cbOnlinePayment.setChecked(false);
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

    @OnClick({R.id.btnOnlinePaymentHelp, R.id.btnPostProduct})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOnlinePaymentHelp:
                break;
            case R.id.btnPostProduct:
                presenter.publishProduct();
                break;
        }
    }


    @OnCheckedChanged({R.id.cbDelivery, R.id.cbFacebook, R.id.cbInstagram, R.id.cbTwitter, R.id.cbOnlinePayment, R.id.cbPickup})
    public void onCheckedChange(CompoundButton view, boolean checked) {
        switch (view.getId()) {
            case R.id.cbDelivery:
                presenter.canDelivery(checked);
                break;
            case R.id.cbPickup:
                presenter.canPickup(checked);
                break;
            case R.id.cbFacebook:
                presenter.shouldPostFacebook(checked);
                break;
            case R.id.cbInstagram:
                presenter.shouldPostInstagram(checked);
                break;
            case R.id.cbTwitter:
                presenter.shouldPostTwitter(checked);
                break;
            case R.id.cbOnlinePayment:
                double value = 0;
                if(etPrice.getText().length() > 0){
                    value = Double.parseDouble(etPrice.getText().toString());
                }
                presenter.enableOnlinePayment(checked, (float) value);
                break;
        }
    }
    //endregion
}
