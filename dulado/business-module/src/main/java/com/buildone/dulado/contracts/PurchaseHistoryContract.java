package com.buildone.dulado.contracts;

import com.buildone.dulado.model.PurchaseObject;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public interface PurchaseHistoryContract {
    interface View{
        void initRecyclerView();
        void populateRecyclerView(ArrayList<PurchaseObject> items);
        void navigateToPuchaseDetails(PurchaseObject purchase);
    }

    interface Presenter{
        void start();
        void onPurchaseSelected(int position);
    }
}
