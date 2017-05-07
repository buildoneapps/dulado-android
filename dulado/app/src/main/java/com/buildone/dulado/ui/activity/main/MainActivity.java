package com.buildone.dulado.ui.activity.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.buildone.dulado.contracts.MainContract;
import com.buildone.dulado.model.LiveObject;
import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.StoreObject;
import com.buildone.dulado.parcel.ProductParcel;
import com.buildone.dulado.ui.activity.NavDrawerBaseActivity;
import com.buildone.dulado.ui.activity.product.ProductActivity;
import com.buildone.dulado.ui.activity.store.StoreActivity;
import com.buildone.dulado.ui.adapter.holder.LiveHolder;
import com.buildone.dulado.ui.adapter.holder.StoreHolder;
import com.buildone.dulado.utils.PermissionStatusHelper;
import com.buildone.dulado.utils.RecyclerItemClickListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MainActivity extends NavDrawerBaseActivity implements
        MainContract.View,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnMapReadyCallback {

    private static final String TAG = MainActivity.class.getName();
    private List<String> locationPermissions = Arrays.asList(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
    private static final long LOCATION_INTERVAL = 5000;
    private static final long LOCATION_SHORT_INTERVAL = 3000;
    private static final int REQUEST_CODE_PERMISSIONS = 0x32;

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    MainContract.Presenter presenter;

    @Inject
    GoogleApiClient mGoogleApiClient;

    Location location;
    GoogleMap googleMap;

    @BindView(R.id.rvLive)
    RecyclerView rvLive;
    @BindView(R.id.scrollStores)
    DiscreteScrollView scrollStores;
    @BindView(R.id.container_store)
    LinearLayout containerStore;
    @BindView(R.id.btnHideStores)
    ImageView btnHideStores;

    FlexibleAdapter<LiveHolder> liveAdapter;
    FlexibleAdapter<StoreHolder> storeAdapter;


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private int someId;

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
        presenter.onPermissionGranted(permissions, locationPermissions);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        presenter.unsubscribeAll();
        super.onDestroy();
    }

    //endregion

    //region MainContract.View
    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void initLocationService() {
        mGoogleApiClient.connect();
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
    public void initPermissions() {
        boolean needPermission = false;
        for (String permission : locationPermissions) {
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
            ActivityCompat.requestPermissions(this, (String[]) locationPermissions.toArray(), REQUEST_CODE_PERMISSIONS);
            return;
        }
        presenter.onPermissionGranted((String[]) locationPermissions.toArray(), locationPermissions);
    }

  /*  @Override

    <Spinner
        android:id="@+id/spinnerDistance"
        android:layout_width="200dp"
        android:layout_height="36dp"
        android:layout_below="@id/rvLive"
        android:layout_centerHorizontal="true"
        android:layout_margin="16dp"
        android:background="@android:color/white" />

    public void initSpinnerDistance(ArrayList<String> distances) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) distances.toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistance.setAdapter(adapter);
        spinnerDistance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.onDistanceSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setCurrentDistance(int position) {
        spinnerDistance.setSelection(position);
    }
*/
    @Override
    public void populateLiveRecycler(ArrayList<LiveObject> items) {
        ArrayList<LiveHolder> holders = new ArrayList<>();
        for (LiveObject object : items) {
            holders.add(new LiveHolder(this, object));
        }
        liveAdapter.addItems(holders);
    }

    @Override
    public void navigateToSearchActivity() {
        /*Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);*/
    }

    @Override
    public void navigateToProductActivity(ProductObject product) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_PRODUCT_OBJECT, new ProductParcel(product));
        startActivity(intent);
    }

    @Override
    public void navigateToStoreActivity(int storeId) {
        Intent intent = new Intent(this, StoreActivity.class);
        intent.putExtra(AppConstants.INTENT_TAG_STORE_ID, storeId);
        startActivity(intent);
    }

    @Override
    public void loadMapStyle() {
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json)
            );
            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
    }

    @SuppressWarnings({"MissingPermission"})
    @Override
    public void enableMapInteraction() {
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void centerUser() {
        if (location != null && googleMap != null) {
            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(userLocation, 15);
            googleMap.animateCamera(location);
        }
    }

    @Override
    public void showRadius(int radius) {
        /*if (googleMap != null) {
            MapDrawerHelper mapDrawer = new MapDrawerHelper(googleMap);
            mapDrawer.drawCircle(this, new LatLng(location.getLatitude(), location.getLongitude()), radius);
        }*/
    }

    @Override
    public void initStoresScrollView() {
        storeAdapter = new FlexibleAdapter<>();
        scrollStores.setAdapter(storeAdapter);
        scrollStores.setOffscreenItems(0);
        scrollStores.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build());
    }

    @Override
    public void populateStoreScrollView(ArrayList<StoreObject> items) {
        List<StoreHolder> holders = new ArrayList<>();
        for (StoreObject item : items) {
            holders.add(new StoreHolder(this, item));
        }
        storeAdapter.addItems(holders);
        scrollStores.scrollToPosition(1);
    }

    @Override
    public void hideStoresScrollView() {
        if (scrollStores.getVisibility() == View.INVISIBLE) {
            return;
        }
        btnHideStores.setVisibility(View.GONE);

        Transition slide = new Slide(Gravity.BOTTOM);
        slide.setDuration(1200);
        slide.setInterpolator(new AccelerateDecelerateInterpolator());
        slide.addTarget(scrollStores);

        TransitionManager.beginDelayedTransition(containerStore, slide);
        scrollStores.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showStoresScrollView() {
        if (scrollStores.getVisibility() == View.VISIBLE) {
            return;
        }
        btnHideStores.setVisibility(View.VISIBLE);

        Transition slide = new Slide(Gravity.BOTTOM);
        slide.setDuration(1200);
        slide.setInterpolator(new AccelerateDecelerateInterpolator());
        slide.addTarget(scrollStores);

        TransitionManager.beginDelayedTransition(containerStore, slide);
        scrollStores.setVisibility(View.VISIBLE);
    }
    //endregion


    public int getSomeId() {
        return someId;
    }

    //region Button Actions
    @OnClick(R.id.btnHideStores)
    public void onButtonHideStoresTouched() {
        presenter.onButtonHideStoresTouched();
    }

    //endregion
    //region Google Maps API
    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onConnected(@Nullable Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            showToastMessage("Latitude():" + location.getLatitude() + "\nLongitude(): " + location.getLongitude());
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }

        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(LOCATION_INTERVAL);
        mLocationRequest.setFastestInterval(LOCATION_SHORT_INTERVAL);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        presenter.onGoogleConnected();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        if (this.location != null) {
            showToastMessage("Latitude():" + this.location.getLatitude() + "\nLongitude(): " + this.location.getLongitude());
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        presenter.onMapReady();
        googleMap.setOnGroundOverlayClickListener(new GoogleMap.OnGroundOverlayClickListener() {
            @Override
            public void onGroundOverlayClick(GroundOverlay groundOverlay) {
                presenter.onCameraMove();
            }
        });
    }

    //endregion
}
