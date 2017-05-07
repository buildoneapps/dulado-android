package com.buildone.dulado.ui.activity.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.buildone.dulado.R;
import com.buildone.dulado.contracts.SearchContract;
import com.buildone.dulado.ui.activity.BaseActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    SearchContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
    public void setupTabs() {
    }
}
