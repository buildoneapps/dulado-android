package com.buildone.dulado.interactor;

import com.buildone.dulado.model.LiveObject;

import java.util.ArrayList;

import io.reactivex.Observable;


/**
 * Created by Alessandro Pryds on 18/05/2017.
 */

public interface IMainInteractor {
    Observable<ArrayList<LiveObject>> getLiveByPosition(long latitude, long longitude);
    Observable<ArrayList<LiveObject>> getLiveByRegion(String region);
}
