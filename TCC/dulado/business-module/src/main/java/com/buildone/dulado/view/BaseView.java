package com.buildone.dulado.view;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */

public interface BaseView {
    void showToastMessage(String text);
    void showProgress(String message);
    void showProgress();
    void hideProgress();
}
