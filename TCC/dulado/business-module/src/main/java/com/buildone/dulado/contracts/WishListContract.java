package com.buildone.dulado.contracts;

import com.buildone.dulado.model.ProductObject;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public interface WishListContract {
    interface View{
        void initRecyclerView();
        void populateRecyclerView(ArrayList<ProductObject> items);
    }

    interface Presenter{
        void start();
        void onProductSelected(int position);
    }
}
