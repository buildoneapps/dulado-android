package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.view.View;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.event.OnChatButtonTouchedEvent;
import com.buildone.dulado.model.SearchObject;
import com.buildone.rxbus.RxBus;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchGridHolder implements FlexibleHolder {

    /*@BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    */
    private Context context;
    private SearchObject searchObject;
    private Unbinder unbinder;

    public SearchGridHolder(Context context, SearchObject searchObject) {
        this.context = context;
        this.searchObject = searchObject;
    }

    @Override
    public int getLayout() {
        return R.layout.recycler_product_grid_item;
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

        //Glide.with(context).load(searchObject.getPhotoUrl()).placeholder(R.drawable.shape_circle).into(ivLivePhoto);
    }

    @OnClick(R.id.fabChat)
    public void onChatButtonTouched() {
        RxBus.getInstance().publish(new OnChatButtonTouchedEvent(searchObject));
    }
}
