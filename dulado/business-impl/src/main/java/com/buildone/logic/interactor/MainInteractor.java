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
        liveItems.add(new LiveObject(0, 0, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/17438802_1868921930033313_8443440520323137536_a.jpg"));
        liveItems.add(new LiveObject(0, 0, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/11326061_1530830197172381_2032328446_a.jpg"));
        liveItems.add(new LiveObject(0, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/16585149_379674489062905_6897090931841826816_a.jpg"));
        liveItems.add(new LiveObject(0, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/13167239_1035399573193507_1470003426_a.jpg"));
        liveItems.add(new LiveObject(1, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/15625066_1716962028619411_5901183007691112448_a.jpg"));
        liveItems.add(new LiveObject(1, 1, ""));
        liveItems.add(new LiveObject(1, 1, ""));
        return Observable.fromArray(liveItems);
    }

    @Override
    public Observable<ArrayList<LiveObject>> getLiveByRegion(String region) {
        ArrayList<LiveObject> liveItems = new ArrayList<>();
        liveItems.add(new LiveObject(0, 0, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/17438802_1868921930033313_8443440520323137536_a.jpg"));
        liveItems.add(new LiveObject(0, 0, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/11326061_1530830197172381_2032328446_a.jpg"));
        liveItems.add(new LiveObject(0, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/16585149_379674489062905_6897090931841826816_a.jpg"));
        liveItems.add(new LiveObject(0, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/13167239_1035399573193507_1470003426_a.jpg"));
        liveItems.add(new LiveObject(1, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/15625066_1716962028619411_5901183007691112448_a.jpg"));
        liveItems.add(new LiveObject(1, 1, ""));
        liveItems.add(new LiveObject(1, 1, ""));
        return Observable.fromArray(liveItems);
    }
}
