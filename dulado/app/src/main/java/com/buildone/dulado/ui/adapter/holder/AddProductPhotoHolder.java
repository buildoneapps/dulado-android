package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alessandro Pryds on 16/05/2017.
 */

public class AddProductPhotoHolder implements FlexibleHolder {

    @BindView(R.id.ivProductPhoto)
    ImageView ivProductPhoto;

    private final Context context;
    private boolean enabled;
    private String photoPath;
    private Unbinder unbinder;

    public AddProductPhotoHolder(Context context, String photoPath, boolean enabled) {
        this.photoPath = photoPath;
        this.context = context;
        this.enabled = enabled;
    }

    @Override
    public int getLayout() {
        if(enabled){
            return R.layout.recycler_add_product_photo_enabled_item;
        }
        return R.layout.recycler_add_product_photo_disabled_item;
    }

    @Override
    public void displayView(@NonNull View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        if (photoPath == null || photoPath.isEmpty()) {
            //ivProductPhoto.setImageDrawable(R.drawable.ic_photo_enabled);
        } else {
            Glide.with(context).load(photoPath).into(ivProductPhoto);
        }
    }

    @Override
    public void recycle() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
