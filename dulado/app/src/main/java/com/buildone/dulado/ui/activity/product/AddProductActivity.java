package com.buildone.dulado.ui.activity.product;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.contracts.AddProductContract;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.buildone.dulado.ui.adapter.holder.AddProductPhotoHolder;
import com.buildone.dulado.utils.RecyclerItemClickListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class AddProductActivity extends BaseActivity implements AddProductContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    AddProductContract.Presenter presenter;

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
