package com.buildone.dulado.contracts;

import com.buildone.dulado.model.SellingHistory;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 02/05/2017.
 */

public interface SellingHistoryContract {
    interface View extends BaseView {
        void initToolbar();
        void setTotalValueText();
        void initRecyclerView(ArrayList<SellingHistory> items);
        void initSpinnerItens(ArrayList<String> options);

    }

    interface Presenter{
        void start();
        void onHistoryTouched(int position);
        void onFilterChanged(int position);
    }
}
