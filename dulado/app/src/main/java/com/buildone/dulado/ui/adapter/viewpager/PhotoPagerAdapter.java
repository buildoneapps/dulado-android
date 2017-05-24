package com.buildone.dulado.ui.adapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.buildone.dulado.ui.fragments.PhotoFragment;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 07/05/2017.
 */

public class PhotoPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<String> photos;

    public PhotoPagerAdapter(FragmentManager fm, ArrayList<String> photos) {
        super(fm);
        this.photos = photos;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoFragment.newInstance(photos.get(position));
    }

    @Override
    public int getCount() {
        return photos.size();
    }
}
