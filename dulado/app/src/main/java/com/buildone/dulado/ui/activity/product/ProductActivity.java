package com.buildone.dulado.ui.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.ProductContract;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.parcel.ProductParcel;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.buildone.dulado.ui.activity.store.StoreActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProductActivity extends BaseActivity implements ProductContract.View {

    @BindView(R.id.productAddess)
    TextView productAddess;
    @BindView(R.id.ivSellerPhoto)
    CircleImageView ivSellerPhoto;
    @BindView(R.id.tvSellerName)
    TextView tvSellerName;
    @BindView(R.id.vpPhotos)
    ViewPager vpPhotos;
    @BindView(R.id.productName)
    TextView productName;
    @BindView(R.id.productTags)
    TextView productTags;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.productDescription)
    TextView productDescription;
    @BindView(R.id.paymentCard)
    TextView paymentCard;

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    ProductContract.Presenter presenter;

    private ProductParcel product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        product = (ProductParcel) getIntent().getExtras().get(AppConstants.INTENT_TAG_PRODUCT_SEARCH_OBJECT);

        AndroidInjection.inject(this);
        presenter.start();
    }

    @Override
    public void initToolbar(String title, String subtitle) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
    }

    @Override
    public void setProductName(String name) {
        productName.setText(name);
    }

    @Override
    public void setProductTags(ArrayList<String> tags) {

    }

    @Override
    public void setProductDescription(String description) {
        productDescription.setText(description);
    }

    @Override
    public void setProductPrice(String price) {
        tvPrice.setText(price);
    }

    @Override
    public void loadProductImages(ArrayList<String> imageUrls) {

    }

    @Override
    public void setProductQuantity() {

    }

    @Override
    public void setProductWish(boolean enabled) {

    }

    @Override
    public void showProductUnavailableContainer() {

    }

    @Override
    public void navigateToCheckoutActivity(ProductObject product) {
        /*Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_PRODUCT_SEARCH_OBJECT, new ProductSearchParcel(product));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @Override
    public void navigateToChatActivity(ProductObject product) {
        /*Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_PRODUCT_SEARCH_OBJECT, new ProductSearchParcel(product));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @Override
    public void navigateToStoreActivity(int storeId) {
        Intent intent = new Intent(this, StoreActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_STORE_ID, storeId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public ProductObject getProduct() {
        return product.getProduct();
    }

    @OnClick({R.id.btnChat, R.id.btnStore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnChat:
                presenter.onButtonChatTouched();
                break;
            case R.id.btnStore:
                presenter.onButtonStoreTouched();
                break;
        }
    }
}
