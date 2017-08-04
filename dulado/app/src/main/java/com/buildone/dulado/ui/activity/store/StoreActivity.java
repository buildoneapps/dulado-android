package com.buildone.dulado.ui.activity.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.StoreContract;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.parcel.ProductParcel;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.buildone.dulado.ui.activity.UserProfileActivity;
import com.buildone.dulado.ui.activity.product.ProductActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import de.hdodenhof.circleimageview.CircleImageView;

public class StoreActivity extends BaseActivity implements StoreContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    StoreContract.Presenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivStoreHeader)
    ImageView ivStoreHeader;
    @BindView(R.id.storeName)
    TextView storeName;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.productStoreCount)
    TextView productStoreCount;
    @BindView(R.id.btnViewProduct)
    LinearLayout btnViewProduct;
    @BindView(R.id.ivPin)
    ImageView ivPin;
    @BindView(R.id.productAddess)
    TextView productAddess;
    @BindView(R.id.ivSellerPhoto)
    CircleImageView ivSellerPhoto;
    @BindView(R.id.tvSellerName)
    TextView tvSellerName;
    @BindView(R.id.btnUserProfile)
    RelativeLayout btnUserProfile;
    @BindView(R.id.mapView)
    ImageView mapView;
    @BindView(R.id.image0)
    ImageView image0;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.image5)
    ImageView image5;

    private int storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.bind(this);
        this.storeId = getIntent().getExtras().getInt(AppConstants.INTENT_TAG_STORE_ID, -1);

        AndroidInjection.inject(this);
        presenter.start();

        Glide.with(this).load("https://images.template.net/wp-content/uploads/2017/01/20014945/Chocolate-Chip-Cookies-Pattern.jpg").centerCrop().centerCrop().into(ivStoreHeader);
        Glide.with(this).load("https://usercontent2.hubstatic.com/13152731_f520.jpg").centerCrop().into(image1);
        Glide.with(this).load("http://www.ambitiouskitchen.com/wp-content/uploads/2012/09/IMG_5228-e1407129045315.jpg").centerCrop().into(image0);
        Glide.with(this).load("http://assets.simplyrecipes.com/wp-content/uploads/2010/08/chocolate-nutella-cookies-horiz-a-1600.jpg").centerCrop().into(image2);
        Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUtM2RhY7tvVsrbxaBLtXWMjbbtFOwhlaoVVoqbU7xxgYWiwuS").centerCrop().into(image3);
        Glide.with(this).load("https://i2.wp.com/www.onehundreddollarsamonth.com/wp-content/uploads/2014/05/nutella-brownies.jpg").centerCrop().into(image4);
        Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2SdlfLyh4B-la-q759eKH1JVT8s4lpF_0O4RMIUVbxlQ2lsO9Hg").centerCrop().into(image5);
        Glide.with(this).load("https://tr1.cbsistatic.com/hub/i/r/2014/07/09/5ddb5529-bdc9-4656-913d-8cc299ea5e15/resize/770x/245bc93953d16dbfc16d18dd7a6c98af/staticmapgoogle0514.png").centerCrop().into(mapView);

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
