package com.buildone.dulado.contracts;

import com.buildone.dulado.model.PendingSaleObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public interface PendingSalesContract {
    interface View extends BaseView {
        void initListRecyclerView();
        void populateListRecyclerView(ArrayList<PendingSaleObject> items);
        void showEmptyMessage();
    }

    interface Presenter extends Disposable{
        void start();
        void loadProducts();
        void initSubscriptions();
    }
}
