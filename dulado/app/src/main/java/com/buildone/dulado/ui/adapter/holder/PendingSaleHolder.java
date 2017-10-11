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
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Alessandro Pryds on 08/10/2017.
 */

public class PendingSaleHolder implements FlexibleHolder {

    @BindView(R.id.ivProductPhoto)
    ImageView ivProductPhoto;
    @BindView(R.id.ivUserPhoto)
    CircleImageView userPhoto;
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
    public void displayView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        tvProductName.setText(pendingSale.getProductName());
        tvPrice.setText(String.format(context.getString(R.string.text_price), String.format(Locale.getDefault(), "%.2f", pendingSale.getPrice())));
        Glide.with(context).load(pendingSale.getImageUrl()).centerCrop().into(ivProductPhoto);
        Glide.with(context).load("http://is4.mzstatic.com/image/thumb/Purple111/v4/60/65/e9/6065e9a4-8628-d25d-da1f-b432264c7543/source/200x200bb.jpg").into(userPhoto);
    }

    @Override
    public void recycle() {

    }

    @OnClick({R.id.btn_cancel, R.id.btn_accept})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                RxBus.getInstance().publish(new OnPendingSaleStateChangedEvent(pendingSale, false));
                break;
            case R.id.btn_accept:
                RxBus.getInstance().publish(new OnPendingSaleStateChangedEvent(pendingSale, true));
                break;
        }
    }
}
