package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.model.LiveObject;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LiveHolder implements FlexibleHolder {

    @BindView(R.id.ivLivePhoto)
    ImageView ivLivePhoto;

    private Context context;
    private LiveObject liveObject;
    private Unbinder unbinder;

    public LiveHolder(Context context, LiveObject liveObject) {
        this.context = context;
        this.liveObject = liveObject;
    }

    @Override
    public int getLayout() {
        return R.layout.recycler_live_item;
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

        Glide.with(context).load(liveObject.getPhotoUrl()).placeholder(R.drawable.shape_circle).into(ivLivePhoto);
    }
}
