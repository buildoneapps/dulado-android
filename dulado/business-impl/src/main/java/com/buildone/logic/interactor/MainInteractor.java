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
        liveItems.add(new LiveObject(0, 0, "https://pbs.twimg.com/profile_images/730465712263802881/03EfZ3MA.jpg"));
        liveItems.add(new LiveObject(0, 0, "https://2.kekantoimg.com/4McJ_2pv2a2cpqymr4bde6_5L7w=/300x300/s3.amazonaws.com/kekanto_pics/pics/194/665194.jpg"));
        liveItems.add(new LiveObject(0, 1, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/LEGO_logo.svg/1200px-LEGO_logo.svg.png"));
        liveItems.add(new LiveObject(0, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/16585149_379674489062905_6897090931841826816_a.jpg"));
        liveItems.add(new LiveObject(1, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/15625066_1716962028619411_5901183007691112448_a.jpg"));

        return Observable.fromArray(liveItems);
    }

    @Override
    public Observable<ArrayList<LiveObject>> getLiveByRegion(String region) {
        ArrayList<LiveObject> liveItems = new ArrayList<>();
        liveItems.add(new LiveObject(0, 0, "https://instagram.fcwb1-1.fna.fbcdn.net/t51.2885-19/s320x320/22277493_354781141613891_8873070844880879616_n.jpg"));
        liveItems.add(new LiveObject(0, 0, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/11326061_1530830197172381_2032328446_a.jpg"));
        liveItems.add(new LiveObject(0, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/16585149_379674489062905_6897090931841826816_a.jpg"));
        liveItems.add(new LiveObject(0, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/13167239_1035399573193507_1470003426_a.jpg"));
        liveItems.add(new LiveObject(1, 1, "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-19/15625066_1716962028619411_5901183007691112448_a.jpg"));
        liveItems.add(new LiveObject(1, 1, ""));
        liveItems.add(new LiveObject(1, 1, ""));
        return Observable.fromArray(liveItems);
    }
}
