package com.buildone.dulado.ui.adapter.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babic.filip.flexibleadapter.FlexibleHolder;
import com.buildone.dulado.R;
import com.buildone.dulado.model.LiveObject;
import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LiveHolder implements FlexibleHolder {

    @BindView(R.id.ivLivePhoto)
    CircleImageView ivLivePhoto;
    @BindView(R.id.ivBorder)
    ImageView ivBorder;
    @BindView(R.id.tvLive)
    TextView tvLive;
    @BindView(R.id.root)
    RelativeLayout root;
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
        ivBorder.setVisibility(View.GONE);
        tvLive.setVisibility(View.GONE);

        if (liveObject.getUserId() == 0) {
            ivBorder.setVisibility(View.VISIBLE);
        }
        if (liveObject.getStoreId() == 0) {
            tvLive.setVisibility(View.VISIBLE);
            animateView();
        }

        Glide.with(context).load(liveObject.getPhotoUrl()).into(ivLivePhoto);
    }

    private void animateView() {

        Observable.timer(800, TimeUnit.MILLISECONDS)
                .repeat(3)
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(@NonNull Long aLong) {
                        ImageView imageView = new ImageView(context);
                        imageView.setLayoutParams(ivBorder.getLayoutParams());
                        imageView.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.shape_circle_stroke));
                        root.addView(imageView);

                        Animation anim = new ScaleAnimation(
                                0.01f, 0.85f, // Start and end values for the X axis scaling
                                0.01f, 0.85f, // Start and end values for the Y axis scaling
                                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                        anim.setFillAfter(true); // Needed to keep the result of the animation
                        anim.setDuration(700);
                        anim.setRepeatCount(1000);
                        anim.setRepeatMode(Animation.REVERSE);
                        imageView.startAnimation(anim);

                        ivLivePhoto.bringToFront();
                        tvLive.bringToFront();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable.timer(800, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Animation anim = new ScaleAnimation(
                                0.75f, 0.7f, // Start and end values for the X axis scaling
                                0.75f, 0.7f, // Start and end values for the Y axis scaling
                                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                        anim.setFillAfter(true); // Needed to keep the result of the animation
                        anim.setDuration(500);
                        anim.setRepeatCount(1000);
                        anim.setRepeatMode(Animation.REVERSE);
                        ivLivePhoto.startAnimation(anim);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
