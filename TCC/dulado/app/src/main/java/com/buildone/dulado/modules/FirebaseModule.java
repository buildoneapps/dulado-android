package com.buildone.dulado.modules;

import android.content.Context;

import com.buildone.dulado.scope.ForApplication;
import com.google.firebase.analytics.FirebaseAnalytics;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alessandro Pryds on 24/04/2017.
 */
@Module
public class FirebaseModule {

    public FirebaseModule() {

    }

    @Provides
    FirebaseAnalytics providesFirebaseAnalytics(@ForApplication Context context) {
        return FirebaseAnalytics.getInstance(context);
    }
}
