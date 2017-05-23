package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.event.OnChatButtonTouchedEvent;
import com.buildone.dulado.event.OnProductTouchedEvent;
import com.buildone.dulado.event.OnSellerTouchedEvent;
import com.buildone.dulado.model.SearchObject;
import com.buildone.rxbus.RxBus;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchHolder implements FlexibleHolder {

    @BindView(R.id.ivProductPhoto)
    ImageView ivProductPhoto;
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvProducTags)
    TextView tvProducTags;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.ivSellerPhoto)
    CircleImageView ivSellerPhoto;
    @BindView(R.id.tvSellerName)
    TextView tvSellerName;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    /*@BindView(R.id.ivPhoto)
        ImageView ivPhoto;
        */
    private Context context;
    private SearchObject searchObject;
    private Unbinder unbinder;

    public SearchHolder(Context context, SearchObject searchObject) {
        this.context = context;
        this.searchObject = searchObject;
    }

    @Override
    public int getLayout() {
        return R.layout.recycler_product_list_item;
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
        tvPrice.setText("R$ " + String.format("%.2f",searchObject.getPrice()));
        Glide.with(context).load(searchObject.getImageUrl()).centerCrop().placeholder(R.drawable.ic_digital_photo_camera_grey_300).into(ivProductPhoto);
        Glide.with(context).load(searchObject.getSeller().getPhotoUrl()).centerCrop().placeholder(R.drawable.shape_circle).into(ivSellerPhoto);
    }

    @OnClick(R.id.fabChat)
    public void onChatButtonTouched() {
        RxBus.getInstance().publish(new OnChatButtonTouchedEvent(searchObject));
    }

    @OnClick({R.id.ivProductPhoto, R.id.container_info, R.id.container_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivProductPhoto:
                RxBus.getInstance().publish(new OnProductTouchedEvent(searchObject));
                break;
            case R.id.container_info:
                RxBus.getInstance().publish(new OnProductTouchedEvent(searchObject));
                break;
            case R.id.container_store:
                RxBus.getInstance().publish(new OnSellerTouchedEvent(searchObject.getSeller()));
                break;
        }
    }
}
