package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.view.View;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.model.LiveObject;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class LiveHolder implements FlexibleHolder {

    @BindView(R.id.ivLivePhoto)
    CircleImageView ivLivePhoto;
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

        Glide.with(context).load(liveObject.getPhotoUrl()).placeholder(R.drawable.ic_user_live_placeholder).into(ivLivePhoto);
    }
}
