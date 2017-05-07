package com.buildone.dulado.contracts;

import com.buildone.dulado.model.DiscoverObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public interface DiscoverContract {
    interface View extends BaseView {
        void initToolbar();
        void initRecyclerView();
        void populateRecyclerView(ArrayList<DiscoverObject> items);
        void navigateToDiscoverCategory(DiscoverObject discoverObject);
    }

    interface Presenter{
        void start();
        void onItemSelected(int position);
    }
}
