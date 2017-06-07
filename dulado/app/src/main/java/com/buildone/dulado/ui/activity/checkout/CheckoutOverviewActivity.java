package com.buildone.dulado.ui.activity.checkout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buildone.dulado.R;
import com.buildone.dulado.contracts.CheckoutOverviewContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutOverviewActivity extends AppCompatActivity implements CheckoutOverviewContract.View {

    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvProducTags)
    TextView tvProducTags;
    @BindView(R.id.btnDecrease)
    ImageButton btnDecrease;
    @BindView(R.id.tvItemCount)
    TextView tvItemCount;
    @BindView(R.id.btnIncrease)
    ImageButton btnIncrease;
    @BindView(R.id.productPrice)
    TextView productPrice;
    @BindView(R.id.shippingPrice)
    TextView shippingPrice;
    @BindView(R.id.cupomPrice)
    TextView cupomPrice;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    @BindView(R.id.btnCupom)
    Button btnCupom;
    @BindView(R.id.statusDelivery)
    ImageView statusDelivery;
    @BindView(R.id.btnDelivery)
    Button btnDelivery;
    @BindView(R.id.btnPickup)
    Button btnPickup;
    @BindView(R.id.ivDelivery)
    ImageView ivDelivery;
    @BindView(R.id.deliveryText)
    TextView deliveryText;
    @BindView(R.id.container_delivery)
    LinearLayout containerDelivery;
    @BindView(R.id.ivPickup)
    ImageView ivPickup;
    @BindView(R.id.pickupText)
    TextView pickupText;
    @BindView(R.id.statusPaymentMethod)
    ImageView statusPaymentMethod;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.container_payment_method)
    LinearLayout containerPaymentMethod;
    @BindView(R.id.statusOrder)
    ImageView statusOrder;
    @BindView(R.id.tvOrderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.statusPayment)
    ImageView statusPayment;
    @BindView(R.id.tvPaymentStatus)
    TextView tvPaymentStatus;
    @BindView(R.id.statusDelivering)
    ImageView statusDelivering;
    @BindView(R.id.statusMeetSeller)
    ImageView statusMeetSeller;
    @BindView(R.id.mapView)
    ImageView mapView;
    @BindView(R.id.statusReview)
    ImageView statusReview;
    @BindView(R.id.btnLike)
    Button btnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_overview);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void setProductName(String productName) {

    }

    @Override
    public void setProductTag(String tags) {

    }

    @Override
    public void setQuantity(int quantity) {

    }

    @Override
    public void setShippingSelectedStyle() {

    }

    @Override
    public void setPaymentSelectedStyle() {

    }

    @Override
    public void openStatus(int status) {

    }

    @Override
    public void closeStatus(int status) {

    }

    @Override
    public void hidePaymentMethod() {

    }

    @Override
    public void showPaymentSelected() {

    }

    @Override
    public void showPaymentButton() {

    }

    @Override
    public void hidePaymentButton() {

    }

    @Override
    public void loadMap() {

    }
}
