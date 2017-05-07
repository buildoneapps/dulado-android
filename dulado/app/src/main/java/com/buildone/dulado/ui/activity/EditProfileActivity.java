package com.buildone.dulado.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.buildone.dulado.R;
import com.buildone.dulado.contracts.EditProfileContract;
import com.buildone.dulado.model.UserObject;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class EditProfileActivity extends BaseActivity implements EditProfileContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    EditProfileContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

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
    public void loadUserPhoto(String imageUrl) {

    }

    @Override
    public void setUserDetailText(UserObject user) {

    }

    @Override
    public void navigateToWalletManager() {

    }

    @Override
    public void showPhotoPickerChooser() {

    }

    @Override
    public void checkPermissions() {

    }

    @Override
    public void openCamera() {

    }

    @Override
    public void openGallery() {

    }
}
