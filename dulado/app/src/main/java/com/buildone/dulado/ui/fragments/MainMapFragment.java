package com.buildone.dulado.ui.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.babic.filip.flexibleadapter.FlexibleAdapter;
import com.buildone.dulado.R;
import com.buildone.dulado.contracts.MainMapContract;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.ui.adapter.holder.MainStoreHolder;
import com.buildone.dulado.utils.PermissionStatusHelper;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
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
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class MainMapFragment extends BaseFragment implements MainMapContract.View,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnMapReadyCallback {
    private static final String TAG = MainMapFragment.class.getName();
    private List<String> locationPermissions = Arrays.asList(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
    private static final long LOCATION_INTERVAL = 5000;
    private static final long LOCATION_SHORT_INTERVAL = 3000;
    private static final int REQUEST_CODE_PERMISSIONS = 0x32;

    @Inject
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    MainMapContract.Presenter presenter;

    @Inject
    GoogleApiClient mGoogleApiClient;

    Location location;
    GoogleMap googleMap;

    @BindView(R.id.scrollStores)
    DiscreteScrollView scrollStores;
    @BindView(R.id.container_store)
    LinearLayout containerStore;
    @BindView(R.id.btnHideStores)
    ImageView btnHideStores;

    FlexibleAdapter<MainStoreHolder> storeAdapter;

    Unbinder unbinder;

    public MainMapFragment() {
        // Required empty public constructor
    }

    public static MainMapFragment newInstance() {
        MainMapFragment fragment = new MainMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_map, container, false);
        unbinder = ButterKnife.bind(this, view);

        presenter.start();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        presenter.disposeAll();
        super.onDestroy();
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
    public void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void initLocationService() {
        mGoogleApiClient.connect();
    }

    @Override
    public void initPermissions() {
        boolean needPermission = false;
        for (String permission : locationPermissions) {
            switch (PermissionStatusHelper.getPermissionStatus(getActivity(), permission)) {
                case PermissionStatusHelper.BLOCKED_OR_NEVER_ASKED:
                    needPermission = true;
                    break;
                case PermissionStatusHelper.DENIED:
                    needPermission = true;
                    break;
            }
        }
        if (needPermission) {
            ActivityCompat.requestPermissions(getActivity(), (String[]) locationPermissions.toArray(), REQUEST_CODE_PERMISSIONS);
            return;
        }
        presenter.onPermissionGranted((String[]) locationPermissions.toArray(), locationPermissions);
    }


    @Override
    public void loadMapStyle() {
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json)
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
    public void populateStoreScrollView(ArrayList<SearchObject> items) {
        List<MainStoreHolder> holders = new ArrayList<>();
        for (SearchObject item : items) {
            holders.add(new MainStoreHolder(getActivity(), item));
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
            //Toast.makeText(getActivity(), "Latitude():" + location.getLatitude() + "\nLongitude(): " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Location not Detected", Toast.LENGTH_SHORT).show();
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
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(location.getLatitude(),location.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_store_png))
                    .anchor(0.5f, 1)
            );
            Toast.makeText(getActivity(), "Latitude():" + this.location.getLatitude() + "\nLongitude(): " + this.location.getLongitude(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        presenter.onMapReady();
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.setOnGroundOverlayClickListener(new GoogleMap.OnGroundOverlayClickListener() {
            @Override
            public void onGroundOverlayClick(GroundOverlay groundOverlay) {
                presenter.onCameraMove();
            }
        });
    }
}
