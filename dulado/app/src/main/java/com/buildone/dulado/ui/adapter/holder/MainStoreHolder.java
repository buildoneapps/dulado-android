package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.model.SearchObject;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainStoreHolder implements FlexibleHolder {

    @BindView(R.id.ivProductPhoto)
    ImageView ivProductPhoto;
    private Context context;
    private SearchObject searchObject;
    private Unbinder unbinder;

    public MainStoreHolder(Context context, SearchObject searchObject) {
        this.context = context;
        this.searchObject = searchObject;
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
        Glide.with(context).load(searchObject.getImageUrl()).centerCrop().into(ivProductPhoto);
    }

    @OnClick({R.id.ivProductPhoto, R.id.ivSellerPhoto})
    public void onViewClicked(View view) {
        /*switch (view.getId()) {
            case R.id.ivProductPhoto:
                RxBus.getInstance().publish(new OnProductTouchedEvent(storeObject.getProductSearch()));
                break;
            case R.id.ivSellerPhoto:
                RxBus.getInstance().publish(new OnStoreTouchedEvent(storeObject));
                break;
        }*/
    }
}
