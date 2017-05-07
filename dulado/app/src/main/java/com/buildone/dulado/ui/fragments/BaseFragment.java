package com.buildone.dulado.ui.fragments;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.buildone.dulado.view.BaseView;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class BaseFragment extends Fragment implements BaseView {

    @Override
    public void showToastMessage(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
