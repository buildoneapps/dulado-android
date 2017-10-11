package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.event.OnPendingSaleStateChangedEvent;
import com.buildone.dulado.model.PendingSaleObject;
import com.buildone.rxbus.RxBus;
import com.bumptech.glide.Glide;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Alessandro Pryds on 08/10/2017.
 */

public class PendingSaleHolder implements FlexibleHolder {

    @BindView(R.id.ivProductPhoto)
    ImageView ivProductPhoto;
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    private Context context;
    private PendingSaleObject pendingSale;
    private Unbinder unbinder;

    public PendingSaleHolder(Context context, PendingSaleObject pendingSale) {
        this.context = context;
        this.pendingSale = pendingSale;
    }

    @Override
    public int getLayout() {
        return R.layout.recycler_pending_sale_item;
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
        tvProductName.setText(pendingSale.getProductName());
        tvPrice.setText(String.format(context.getString(R.string.text_price), String.format(Locale.getDefault(), "%.2f", pendingSale.getPrice())));
        Glide.with(context).load(pendingSale.getImageUrl()).centerCrop().into(ivProductPhoto);
    }

    @OnClick({R.id.ivProductPhoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivProductPhoto:
                RxBus.getInstance().publish(new OnPendingSaleStateChangedEvent(pendingSale));
                break;
        }
    }
}
