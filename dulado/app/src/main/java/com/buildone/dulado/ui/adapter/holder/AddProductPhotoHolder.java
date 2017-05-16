package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.event.OnAddProductPhotoRequestEvent;
import com.buildone.rxbus.RxBus;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Alessandro Pryds on 16/05/2017.
 */

public class AddProductPhotoHolder implements FlexibleHolder {

    @BindView(R.id.ivProductPhoto)
    ImageView ivProductPhoto;

    private final Context context;
    private int position;
    private String photoPath;
    private boolean enabled;
    private Unbinder unbinder;

    public AddProductPhotoHolder(Context context, String photoPath, boolean enabled, int position) {
        this.photoPath = photoPath;
        this.enabled = enabled;
        this.context = context;
        this.position = position;
    }

    @Override
    public int getLayout() {
        return R.layout.recycler_add_product_photo_item;
    }

    @Override
    public void displayView(@NonNull View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        if (enabled) {
            //ivProductPhoto.setImageDrawable(R.drawable.ic_photo_enabled);
        } else if (photoPath != null) {
            Glide.with(context).load(photoPath).into(ivProductPhoto);
        }
    }

    @Override
    public void recycle() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    @OnClick(R.id.ivProductPhoto)
    public void onPhotoTouched() {
        RxBus.getInstance().publish(new OnAddProductPhotoRequestEvent(position));
    }
}
