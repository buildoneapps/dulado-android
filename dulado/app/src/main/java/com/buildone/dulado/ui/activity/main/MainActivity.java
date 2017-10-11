package com.buildone.dulado.ui.activity.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.MainContract;
import com.buildone.dulado.event.OnListFormatModeChangedEvent;
import com.buildone.dulado.model.LiveObject;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.parcel.ProductSearchParcel;
import com.buildone.dulado.ui.activity.NavDrawerBaseActivity;
import com.buildone.dulado.ui.activity.product.AddProductActivity;
import com.buildone.dulado.ui.activity.product.ProductActivity;
import com.buildone.dulado.ui.activity.store.StoreActivity;
import com.buildone.dulado.ui.adapter.holder.LiveHolder;
import com.buildone.dulado.ui.adapter.viewpager.MainPagerAdapter;
import com.buildone.dulado.utils.BottomNavigationHelper;
import com.buildone.dulado.utils.CameraIntentHelper;
import com.buildone.dulado.utils.CameraIntentHelperCallback;
import com.buildone.dulado.utils.NonSwipeableViewPager;
import com.buildone.dulado.utils.PermissionStatusHelper;
import com.buildone.dulado.utils.RecyclerItemClickListener;
import com.buildone.rxbus.RxBus;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends NavDrawerBaseActivity implements MainContract.View, CameraIntentHelperCallback {

    private static final int MAP_FRAG_POS = 0;
    private static final int LIST_FRAG_POS = 1;
    private static final int REQUEST_CODE_PERMISSIONS = 0x32;
    private static final int REQUEST_CODE_ADD_PRODUCT = 0x42;

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    MainContract.Presenter presenter;

    @Inject
    CameraIntentHelper cameraHelper;

    @BindView(R.id.rvLive)
    RecyclerView rvLive;
    @BindView(R.id.viewPager)
    NonSwipeableViewPager viewPager;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.bottomNav)
    BottomNavigationView bottomNav;
    @BindView(R.id.liveProgress)
    ProgressBar liveProgress;

    private int someId;

    FlexibleAdapter<LiveHolder> liveAdapter;
    private MainPagerAdapter vpAdapter;
    private boolean showMapIcon;
    private boolean inGridMode;
    private List<String> cameraPermissions = Arrays.asList(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);


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
    protected void onDestroy() {
        presenter.disposeAll();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        switch (viewPager.getCurrentItem()) {
            case 0:
                inflater.inflate(R.menu.menu_main, menu);
                menu.findItem(R.id.action_map).setVisible(showMapIcon);
                menu.findItem(R.id.action_list).setVisible(inGridMode);
                menu.findItem(R.id.action_grid).setVisible(!inGridMode);
                break;
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        presenter.onPermissionFailed();
                        return;
                    }
                }
                break;
        }
        presenter.onPermissionGranted(permissions, cameraPermissions);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_ADD_PRODUCT:
                ProductSearchParcel parcel = (ProductSearchParcel) data.getExtras().get(AppConstants.INTENT_TAG_PRODUCT_SEARCH_OBJECT);
                presenter.addProduct(parcel.getSearchObject());
                break;
            default:
                cameraHelper.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }


    //region MainContract.View
    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name_space));
        getSupportActionBar().setIcon(R.drawable.ic_shop_market);
    }


    @Override
    public void initViewPager() {
        vpAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(vpAdapter);
        viewPager.setOffscreenPageLimit(4);
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
    public void navigateToProductActivity(SearchObject searchObject) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_PRODUCT_SEARCH_OBJECT, new ProductSearchParcel(searchObject));
        startActivity(intent);
    }

    @Override
    public void navigateToChatActivity(SearchObject product, String phoneNumber) {
        try {
            phoneNumber = phoneNumber.replace("+", "").replace(" ", "");

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.putExtra("jid", phoneNumber + "@s.whatsapp.net");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Oi tudo bem? Me interessei pelo(a) " + product.getProductName() + ", mas tenho uma dúvida: ");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setPackage("com.whatsapp");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void navigateToSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToAddProductActivity(String photoUri) {
        Intent intent = new Intent(this, AddProductActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_PHOTO_URI, photoUri);
        startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT);
    }

    @Override
    public int getSomeId() {
        return someId;
    }

    @Override
    public void checkCameraPermission() {
        boolean needPermission = false;
        for (String permission : cameraPermissions) {
            switch (PermissionStatusHelper.getPermissionStatus(this, permission)) {
                case PermissionStatusHelper.BLOCKED_OR_NEVER_ASKED:
                    needPermission = true;
                    break;
                case PermissionStatusHelper.DENIED:
                    needPermission = true;
                    break;
            }
        }
        if (needPermission) {
            ActivityCompat.requestPermissions(this, (String[]) cameraPermissions.toArray(), REQUEST_CODE_PERMISSIONS);
            return;
        }
        presenter.onPermissionGranted((String[]) cameraPermissions.toArray(), cameraPermissions);
    }

    @Override
    public void showCameraPermissionRequiredMessage() {

    }

    @Override
    public void openCamera() {
        cameraHelper.startCameraIntent();
    }

    @Override
    public void showCouldNotTakePhotoMessage() {

    }

    @Override
    public void setupBottomNavigation() {
        BottomNavigationHelper.disableShiftMode(bottomNav);
        BottomNavigationHelper.removeTextLabel(bottomNav, R.id.menu_camera);
        BottomNavigationHelper.removeTextLabel(bottomNav, R.id.menu_grid);
        BottomNavigationHelper.removeTextLabel(bottomNav, R.id.menu_home);
        BottomNavigationHelper.removeTextLabel(bottomNav, R.id.menu_inbox);
        BottomNavigationHelper.removeTextLabel(bottomNav, R.id.menu_profile);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        viewPager.setCurrentItem(0);
                        Slide slideIn = new Slide(Gravity.RIGHT);
                        ChangeBounds boundsIn = new ChangeBounds();
                        TransitionSet setIn = new TransitionSet()
                                .addTransition(slideIn)
                                .addTransition(boundsIn);

                        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root), setIn);
                        rvLive.setVisibility(View.VISIBLE);
                        break;
                    case R.id.menu_grid:
                        viewPager.setCurrentItem(2);
                        Slide slide = new Slide(Gravity.TOP);
                        ChangeBounds bounds = new ChangeBounds();
                        TransitionSet set = new TransitionSet()
                                .addTransition(slide)
                                .addTransition(bounds);

                        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root), set);
                        rvLive.setVisibility(View.GONE);
                        break;
                    case R.id.menu_camera:
                        presenter.openCamera();
                        break;
                    case R.id.menu_inbox:
                        break;
                    case R.id.menu_profile:
                        break;
                }
                invalidateOptionsMenu();
                return true;
            }
        });
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
      /*  Transition transition = new Slide(Gravity.BOTTOM)
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator())
                .addTarget(containerCreateAd);
        TransitionManager.beginDelayedTransition(containerCreateAd, transition);
        containerCreateAd.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void hideCreateAdButton() {
       /* Transition transition = new Slide(Gravity.BOTTOM)
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator())
                .addTarget(containerCreateAd);
        TransitionManager.beginDelayedTransition(containerCreateAd, transition);
        containerCreateAd.setVisibility(View.GONE);*/
    }
    //endregion

    //region Button Actions
   /* @OnClick(R.id.container_create_ad)
    protected void onCreateAdTouched() {
        presenter.onButtonCreateAdTouched();
    }*/
    //endregion

    //region CameraIntentHelperCallback
    @Override
    public void onPhotoUriFound(Date dateCameraIntentStarted, Uri photoUri, int rotateXDegrees) {
        presenter.navigateToAddProduct(photoUri.toString());
    }

    @Override
    public void deletePhotoWithUri(Uri photoUri) {

    }

    @Override
    public void onSdCardNotMounted() {
        presenter.cameraError();
    }

    @Override
    public void onCanceled() {
    }

    @Override
    public void onCouldNotTakePhoto() {
        presenter.cameraError();
    }

    @Override
    public void onPhotoUriNotFound() {
        presenter.cameraError();

    }

    @Override
    public void logException(Exception e) {

    }
    //endregion
}
