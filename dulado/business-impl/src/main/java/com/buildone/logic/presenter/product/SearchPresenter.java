package com.buildone.logic.presenter.product;

import com.buildone.dulado.contracts.SearchContract;
import com.buildone.dulado.interactor.IProductInteractor;

/**
 * Created by Alessandro Pryds on 19/05/2017.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private final IProductInteractor interactor;
    private final SearchContract.View view;

    public SearchPresenter(SearchContract.View view, IProductInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {

    }

    @Override
    public void initSubscriptions() {

    }

    @Override
    public void onItemSelected(int position) {

    }

    @Override
    public void onChangeListModeTouched() {

    }

    @Override
    public void onQueryTextChanged(String query) {

    }

    @Override
    public void onPullToRefresh() {

    }

    @Override
    public void onButtonCreateNewAdTouched() {

    }
}
