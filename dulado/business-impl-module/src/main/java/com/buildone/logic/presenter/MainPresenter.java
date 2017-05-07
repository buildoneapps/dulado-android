package com.buildone.logic.presenter;

import com.buildone.dulado.contracts.MainContract;
import com.buildone.dulado.model.LiveObject;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private final int someId;
    private MainContract.View view;
    private ArrayList<LiveObject> liveItems;
    private boolean inGridMode;

    @Inject
    public MainPresenter(MainContract.View view, @Named("someId") int someId) {
        this.view = view;
        this.someId = someId;
        this.liveItems = new ArrayList<>();
    }

    @Override
    public void start() {
        view.initToolbar();
        view.initViewPager();
        view.showToastMessage(String.valueOf(someId));
        view.initLiveRecycler();

        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        liveItems.add(new LiveObject(0, 1, ""));
        view.populateLiveRecycler(liveItems);
    }


    @Override
    public void onLiveItemTouched(int position) {
        view.navigateToStoreActivity(liveItems.get(position).getStoreId());
    }

    @Override
    public void onButtonMapTouched() {
        inGridMode = !inGridMode;
        view.setMapButtonVisible(false);
        view.setLiveRecyclerVisible(true);
        view.switchToMap(inGridMode);
    }

    @Override
    public void onButtonListTouched() {
        inGridMode = false;
        view.setMapButtonVisible(true);
        view.setLiveRecyclerVisible(false);
        view.switchToList(inGridMode);
    }

    @Override
    public void onButtonGridTouched() {
        inGridMode = true;
        view.setMapButtonVisible(true);
        view.setLiveRecyclerVisible(false);
        view.switchToList(inGridMode);
    }

    @Override
    public void onButtonSearchTouched() {
        view.navigateToSearchActivity();
    }

}
