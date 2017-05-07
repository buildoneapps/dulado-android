package com.buildone.dulado.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.UserProfileContract;
import com.buildone.dulado.model.UserObject;

import dagger.android.AndroidInjection;

public class UserProfileActivity extends BaseActivity implements UserProfileContract.View {

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile);
        this.userId = getIntent().getExtras().getInt(AppConstants.INTENT_TAG_USER_PROFILE_ID);

        AndroidInjection.inject(this);
    }

    @Override
    public void initToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void loadUserData(UserObject user) {

    }

    @Override
    public void expandPhoto() {

    }

    @Override
    public int getUserId() {
        return userId;
    }
}
