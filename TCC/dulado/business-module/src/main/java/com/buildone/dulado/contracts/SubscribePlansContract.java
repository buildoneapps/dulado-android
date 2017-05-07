package com.buildone.dulado.contracts;

/**
 * Created by Alessandro Pryds on 30/04/2017.
 */

public interface SubscribePlansContract {
    interface View{
        void setupSeekBar();
    }

    interface Presenter{
        void start();
        void onSeekBarChanged(int position);
        void onButtonCheckoutTouched();
    }
}
