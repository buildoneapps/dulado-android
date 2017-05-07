package com.buildone.dulado.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.buildone.dulado.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Alessandro Pryds on 24/04/2017.
 */

public class MapDrawerHelper {

    private GoogleMap map;
    private static int EARTH_RADIUS = 6371000;
    private Circle circle;

    public MapDrawerHelper(GoogleMap map) {
        this.map = map;
    }

    private LatLng getPoint(LatLng center, int radius, double angle) {
        // Get the coordinates of a circle point at the given angle
        double east = radius * Math.cos(angle);
        double north = radius * Math.sin(angle);

        double cLat = center.latitude;
        double cLng = center.longitude;
        double latRadius = EARTH_RADIUS * Math.cos(cLat / 180 * Math.PI);

        double newLat = cLat + (north / EARTH_RADIUS / Math.PI * 180);
        double newLng = cLng + (east / latRadius / Math.PI * 180);

        return new LatLng(newLat, newLng);
    }

    public Circle drawCircle(Context context, final LatLng center, final int radius) {
        circle = map.addCircle(new CircleOptions()
                .center(center)
                .radius(radius)
                .strokeWidth(2)
                .fillColor(ContextCompat.getColor(context, R.color.map_circle_color))
        );
        return circle;
    }

    public void animateRadius(int radius) {
        if (circle == null) {
            return;
        }
        Observable.interval(5, TimeUnit.MILLISECONDS)
                .take(radius)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return aLong + 1;
                    }
                }).repeat(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        circle.setRadius(aLong);
                    }
                });

    }
}