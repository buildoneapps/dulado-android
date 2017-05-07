package com.buildone.dulado.ui.adapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.buildone.dulado.ui.fragments.MainMapFragment;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) { switch (position) {
        case 0:
            return MainMapFragment.newInstance();
        case 1:
            return MainMapFragment.newInstance();
        default:
            return null;
    }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
