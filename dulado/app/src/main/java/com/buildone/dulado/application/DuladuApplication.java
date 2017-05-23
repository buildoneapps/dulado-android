package com.buildone.dulado.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.buildone.dulado.R;
import com.buildone.dulado.components.application.DaggerDuladuApplicationComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import dagger.android.support.HasDispatchingSupportFragmentInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */

public class DuladuApplication extends Application implements HasDispatchingActivityInjector, HasDispatchingSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerDuladuApplicationComponent.create().inject(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(AppConstants.FONT_DEFAULT)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }
}
