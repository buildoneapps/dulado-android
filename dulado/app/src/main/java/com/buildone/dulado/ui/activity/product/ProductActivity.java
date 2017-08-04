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
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.parcel.ProductParcel;
import com.buildone.dulado.parcel.ProductSearchParcel;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.buildone.dulado.ui.activity.checkout.CheckoutOverviewActivity;
import com.buildone.dulado.ui.activity.store.StoreActivity;
import com.buildone.dulado.ui.adapter.viewpager.PhotoPagerAdapter;
import com.bumptech.glide.Glide;
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
    @BindView(R.id.like1)
    CircleImageView like1;
    @BindView(R.id.like2)
    CircleImageView like2;
    @BindView(R.id.like3)
    CircleImageView like3;
    @BindView(R.id.lastFriend)
    CircleImageView lastFriend;

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    ProductContract.Presenter presenter;

    private ProductParcel product;
    private ProductSearchParcel productSearch;
    private PhotoPagerAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        product = (ProductParcel) getIntent().getExtras().get(AppConstants.INTENT_TAG_PRODUCT_OBJECT);
        productSearch = (ProductSearchParcel) getIntent().getExtras().get(AppConstants.INTENT_TAG_PRODUCT_SEARCH_OBJECT);

        AndroidInjection.inject(this);
        presenter.start();

        Glide.with(this).load("https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/17438802_1868921930033313_8443440520323137536_a.jpg").centerCrop().into(like1);
        Glide.with(this).load("https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/11326061_1530830197172381_2032328446_a.jpg").centerCrop().into(like2);
        Glide.with(this).load("https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/16585149_379674489062905_6897090931841826816_a.jpg").centerCrop().into(like3);
        Glide.with(this).load("https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/13167239_1035399573193507_1470003426_a.jpg").centerCrop().into(lastFriend);
    }

    @Override
    protected void onDestroy() {
        presenter.disposeAll();
        super.onDestroy();
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
    public void setProductTags(String tags) {
        productTags.setText(tags);
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
        vpAdapter = new PhotoPagerAdapter(getSupportFragmentManager(), imageUrls);
        vpPhotos.setAdapter(vpAdapter);
        vpPhotos.setCurrentItem(0);
    }

    @Override
    public void setProductWish(boolean enabled) {

    }

    @Override
    public void setSellerName(String name) {
        tvSellerName.setText(name);
    }

    @Override
    public void setSellerPhoto(String photoUrl) {
        Glide.with(this).load(photoUrl).centerCrop().placeholder(R.drawable.shape_circle).into(ivSellerPhoto);
    }

    @Override
    public void showProductUnavailableContainer() {

    }

    @Override
    public void navigateToCheckoutActivity(ProductObject product) {
        Intent intent = new Intent(this, CheckoutOverviewActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_PRODUCT_OBJECT, new ProductParcel(product));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
        if (product != null) {
            return product.getProduct();
        }
        return new ProductObject();
    }

    @Override
    public SearchObject getProductSearch() {
        if (productSearch != null) {
            return productSearch.getSearchObject();
        }
        return new SearchObject();
    }

    @OnClick({R.id.btnChat, R.id.btnStore, R.id.btnPurchase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnChat:
                presenter.openChat();
                break;
            case R.id.btnStore:
                presenter.goToStore();
                break;
            case R.id.btnPurchase:
                presenter.goToCheckout();
                break;
        }
    }
}
