package com.buildone.dulado.application;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.buildone.dulado.components.activity.AddProductActivitySubComponent;
import com.buildone.dulado.components.activity.CheckoutOverviewActivitySubComponent;
import com.buildone.dulado.components.activity.DiscoverActivitySubComponent;
import com.buildone.dulado.components.activity.EditProfileActivitySubComponent;
import com.buildone.dulado.components.activity.MainActivitySubComponent;
import com.buildone.dulado.components.activity.ProductActivitySubComponent;
import com.buildone.dulado.components.activity.SearchActivitySubComponent;
import com.buildone.dulado.components.activity.StoreActivitySubComponent;
import com.buildone.dulado.components.activity.UserProfileActivitySubComponent;
import com.buildone.dulado.components.fragment.MainListFragmentSubComponent;
import com.buildone.dulado.components.fragment.MainMapFragmentSubComponent;
import com.buildone.dulado.components.fragment.PendingSalesFragmentSubComponent;
import com.buildone.dulado.ui.activity.DiscoverActivity;
import com.buildone.dulado.ui.activity.EditProfileActivity;
import com.buildone.dulado.ui.activity.UserProfileActivity;
import com.buildone.dulado.ui.activity.checkout.CheckoutOverviewActivity;
import com.buildone.dulado.ui.activity.main.MainActivity;
import com.buildone.dulado.ui.activity.main.SearchActivity;
import com.buildone.dulado.ui.activity.product.AddProductActivity;
import com.buildone.dulado.ui.activity.product.ProductActivity;
import com.buildone.dulado.ui.activity.store.StoreActivity;
import com.buildone.dulado.ui.fragments.MainListFragment;
import com.buildone.dulado.ui.fragments.MainMapFragment;
import com.buildone.dulado.ui.fragments.PendingSalesListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by Alessandro Pryds on 11/04/2017.
 */

@Module(subcomponents = {
        MainActivitySubComponent.class
})
public abstract class BindingModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjectorFactory(MainActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(DiscoverActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindDiscoverActivityInjectorFactory(DiscoverActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(StoreActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindStoreActivityInjectorFactory(StoreActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(UserProfileActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindUserProfileActivityInjectorFactory(UserProfileActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(ProductActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindProductActivityInjectorFactory(ProductActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(AddProductActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAddProductActivityInjectorFactory(AddProductActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(EditProfileActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindEditProfileActivityInjectorFactory(EditProfileActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSearchActivityInjectorFactory(SearchActivitySubComponent.Builder builder);


    @Binds
    @IntoMap
    @ActivityKey(CheckoutOverviewActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindCheckoutOverviewActivityInjectorFactory(CheckoutOverviewActivitySubComponent.Builder builder);


    @Binds
    @IntoMap
    @FragmentKey(MainMapFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMainMapFragmentInjectorFactory(MainMapFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(MainListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMainListFragmentInjectorFactory(MainListFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(PendingSalesListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindPendingSalesListFragmentInjectorFactory(PendingSalesFragmentSubComponent.Builder builder);

}