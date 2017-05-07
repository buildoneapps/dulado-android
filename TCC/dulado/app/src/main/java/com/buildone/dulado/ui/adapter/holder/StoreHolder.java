package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.event.OnProductTouchedEvent;
import com.buildone.dulado.event.OnStoreTouchedEvent;
import com.buildone.dulado.model.StoreObject;
import com.buildone.rxbus.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StoreHolder implements FlexibleHolder {

    @BindView(R.id.ivStoreImage)
    ImageView ivStoreImage;
    private Context context;
    private StoreObject storeObject;
    private Unbinder unbinder;

    public StoreHolder(Context context, StoreObject storeObject) {
        this.context = context;
        this.storeObject = storeObject;
    }

    @Override
    public int getLayout() {
        return R.layout.recycler_store_main_item;
    }

    @Override
    public void recycle() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void displayView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);

    }

    @OnClick({R.id.ivStoreImage, R.id.btnStore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivStoreImage:
                RxBus.getInstance().publish(new OnProductTouchedEvent(storeObject.getProduct()));
                break;
            case R.id.btnStore:
                RxBus.getInstance().publish(new OnStoreTouchedEvent(storeObject));
                break;
        }
    }
}
