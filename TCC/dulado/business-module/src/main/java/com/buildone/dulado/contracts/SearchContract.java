package com.buildone.dulado.contracts;

import com.buildone.dulado.view.BaseView;

/**
 * Created by Alessandro Pryds on 25/04/2017.
 */

public interface SearchContract {
    interface View extends BaseView{
        void initToolbar();
        void setupTabs();
    }

    interface Presenter{
        void start();
        void initSubscriptions();
        void onItemSelected(int position);
        void onChangeListModeTouched();
        void onQueryTextChanged(String query);
        void onPullToRefresh();
        void onButtonCreateNewAdTouched();
    }
}
