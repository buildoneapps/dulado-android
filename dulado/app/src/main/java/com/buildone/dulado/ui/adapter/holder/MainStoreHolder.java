package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.event.OnProductTouchedEvent;
import com.buildone.dulado.model.SearchObject;
import com.buildone.rxbus.RxBus;
import com.bumptech.glide.Glide;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainStoreHolder implements FlexibleHolder {

    @BindView(R.id.ivProductPhoto)
    ImageView ivProductPhoto;
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvProducTags)
    TextView tvProducTags;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
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
        tvProductName.setText(searchObject.getProductName());
        tvProducTags.setText(searchObject.getTag());
        tvPrice.setText(String.format(context.getString(R.string.text_price),String.format(Locale.getDefault(),"%.2f",searchObject.getPrice())));
        Glide.with(context).load(searchObject.getImageUrl()).centerCrop().into(ivProductPhoto);
    }

    @OnClick({R.id.ivProductPhoto, R.id.ivSellerPhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivProductPhoto:
                RxBus.getInstance().publish(new OnProductTouchedEvent(searchObject));
                break;
            case R.id.ivSellerPhoto:
                //RxBus.getInstance().publish(new OnStoreTouchedEvent(searchObject.getSeller()));
                break;
        }
    }
}
