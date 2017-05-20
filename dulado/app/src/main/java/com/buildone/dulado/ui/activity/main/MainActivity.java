package com.buildone.dulado.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.MainContract;
import com.buildone.dulado.event.OnListFormatModeChangedEvent;
import com.buildone.dulado.model.LiveObject;
import com.buildone.dulado.ui.activity.NavDrawerBaseActivity;
import com.buildone.dulado.ui.activity.product.AddProductActivity;
import com.buildone.dulado.ui.activity.store.StoreActivity;
import com.buildone.dulado.ui.adapter.holder.LiveHolder;
import com.buildone.dulado.ui.adapter.viewpager.MainPagerAdapter;
import com.buildone.dulado.utils.NonSwipeableViewPager;
import com.buildone.dulado.utils.RecyclerItemClickListener;
import com.buildone.rxbus.RxBus;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MainActivity extends NavDrawerBaseActivity implements MainContract.View {

    private static final int MAP_FRAG_POS = 0;
    private static final int LIST_FRAG_POS = 1;
    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    MainContract.Presenter presenter;

    @BindView(R.id.rvLive)
    RecyclerView rvLive;
    @BindView(R.id.viewPager)
    NonSwipeableViewPager viewPager;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.container_create_ad)
    LinearLayout containerCreateAd;
    @BindView(R.id.liveProgress)
    ProgressBar liveProgress;

    private int someId;

    FlexibleAdapter<LiveHolder> liveAdapter;
    private MainPagerAdapter vpAdapter;
    private boolean showMapIcon;
    private boolean inGridMode;

    //region Android Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        someId = 2;

        ButterKnife.bind(this);
        AndroidInjection.inject(this);

        presenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_map).setVisible(showMapIcon);
        menu.findItem(R.id.action_list).setVisible(inGridMode);
        menu.findItem(R.id.action_grid).setVisible(!inGridMode);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                presenter.onButtonSearchTouched();
                break;
            case R.id.action_map:
                presenter.onButtonMapTouched();
                break;
            case R.id.action_list:
                presenter.onButtonListTouched();
                RxBus.getInstance().publish(new OnListFormatModeChangedEvent(AppConstants.LIST_MODE_VERTICAL_LIST));
                break;
            case R.id.action_grid:
                presenter.onButtonGridTouched();
                RxBus.getInstance().publish(new OnListFormatModeChangedEvent(AppConstants.LIST_MODE_GRID));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //region MainContract.View
    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initViewPager() {
        vpAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(vpAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void initLiveRecycler() {
        rvLive.setHasFixedSize(true);
        rvLive.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        liveAdapter = new FlexibleAdapter<>();
        rvLive.setAdapter(liveAdapter);
        rvLive.addOnItemTouchListener(new RecyclerItemClickListener(this, rvLive, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.onLiveItemTouched(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }


    @Override
    public void populateLiveRecycler(ArrayList<LiveObject> items) {
        ArrayList<LiveHolder> holders = new ArrayList<>();
        for (LiveObject object : items) {
            holders.add(new LiveHolder(this, object));
        }
        liveAdapter.addItems(holders);
    }

    @Override
    public void navigateToStoreActivity(int storeId) {
        Intent intent = new Intent(this, StoreActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_STORE_ID, storeId);
        startActivity(intent);
    }

    @Override
    public void navigateToSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToAddProductActivity() {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    @Override
    public int getSomeId() {
        return someId;
    }

    @Override
    public void switchToMap(boolean inGridMode) {
        this.inGridMode = inGridMode;
        viewPager.setCurrentItem(MAP_FRAG_POS);
        invalidateOptionsMenu();
    }

    @Override
    public void switchToList(boolean inGridMode) {
        this.inGridMode = inGridMode;
        viewPager.setCurrentItem(LIST_FRAG_POS);
        invalidateOptionsMenu();
    }

    @Override
    public void setMapButtonVisible(boolean visibile) {
        this.showMapIcon = visibile;
    }

    @Override
    public void setLiveRecyclerVisible(boolean visible) {
        Transition transition = null;
        if (visible) {
            transition = new Slide(Gravity.START);
            transition.setDuration(500);
            transition.setInterpolator(new AccelerateDecelerateInterpolator());
            transition.addTarget(rvLive);
        } else {
            transition = new ChangeBounds();
            transition.setDuration(500);
            transition.setInterpolator(new AccelerateDecelerateInterpolator());
            transition.addTarget(appBarLayout);
        }
        TransitionManager.beginDelayedTransition(appBarLayout, transition);
        rvLive.setVisibility(visible ? View.VISIBLE : View.GONE);

    }

    @Override
    public void setLiveProgressVisible(boolean visible) {
        liveProgress.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showCreateAdButton() {
        Transition transition = new Slide(Gravity.BOTTOM)
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator())
                .addTarget(containerCreateAd);
        TransitionManager.beginDelayedTransition(containerCreateAd, transition);
        containerCreateAd.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCreateAdButton() {
        Transition transition = new Slide(Gravity.BOTTOM)
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator())
                .addTarget(containerCreateAd);
        TransitionManager.beginDelayedTransition(containerCreateAd, transition);
        containerCreateAd.setVisibility(View.GONE);
    }
    //endregion

    @OnClick(R.id.container_create_ad)
    protected void onCreateAdTouched() {
        presenter.onButtonCreateAdTouched();
    }
}
