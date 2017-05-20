package com.buildone.logic.presenter;

import com.buildone.dulado.contracts.DiscoverContract;
import com.buildone.dulado.interactor.IDiscoverInteractor;
import com.buildone.dulado.model.DiscoverObject;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 02/05/2017.
 */

public class DiscoverPresenter implements DiscoverContract.Presenter {
    private final DiscoverContract.View view;
    private final IDiscoverInteractor interactor;
    private ArrayList<DiscoverObject> loadedCategories;

    public DiscoverPresenter(DiscoverContract.View view, IDiscoverInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initToolbar();
        view.initRecyclerView();
    }

    @Override
    public void onItemSelected(int position) {
        view.navigateToDiscoverCategory(loadedCategories.get(position));
    }
}
