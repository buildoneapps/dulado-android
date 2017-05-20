package com.buildone.logic.interactor;

import com.buildone.dulado.interactor.IMainInteractor;
import com.buildone.dulado.model.LiveObject;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Alessandro Pryds on 18/05/2017.
 */

public class MainInteractor implements IMainInteractor {

    @Override
    public Observable<ArrayList<LiveObject>> getLiveByPosition(long latitude, long longitude) {
        ArrayList<LiveObject> liveItems = new ArrayList<>();
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        return Observable.fromArray(liveItems);
    }

    @Override
    public Observable<ArrayList<LiveObject>> getLiveByRegion(String region) {
        ArrayList<LiveObject> liveItems = new ArrayList<>();
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        return Observable.fromArray(liveItems);
    }
}
