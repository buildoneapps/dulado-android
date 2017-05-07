package com.buildone.dulado.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.buildone.dulado.R;
import com.buildone.dulado.contracts.ChatContract;
import com.buildone.dulado.model.ChatMessage;
import com.buildone.dulado.model.ProductObject;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ChatActivity extends BaseActivity implements ChatContract.View {

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    ChatContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

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
    public void disableSendButton() {

    }

    @Override
    public void loadMap(double latitude, double longitude) {

    }

    @Override
    public void initRecyclerView(ArrayList<ChatMessage> items) {

    }

    @Override
    public void addMessage(ChatMessage message) {

    }

    @Override
    public void getProduct(ProductObject productObject) {

    }

    @Override
    public void setProductDescription(ProductObject product) {

    }

    @Override
    public void navigateToProductActivity(ProductObject product) {

    }
}
