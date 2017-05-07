package com.buildone.dulado.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.buildone.dulado.R;
import com.buildone.dulado.contracts.DiscoverContract;
import com.buildone.dulado.model.DiscoverObject;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class DiscoverActivity extends BaseActivity implements DiscoverContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    DiscoverContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

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
    public void initRecyclerView() {

    }

    @Override
    public void populateRecyclerView(ArrayList<DiscoverObject> items) {

    }

    @Override
    public void navigateToDiscoverCategory(DiscoverObject discoverObject) {

    }
}
