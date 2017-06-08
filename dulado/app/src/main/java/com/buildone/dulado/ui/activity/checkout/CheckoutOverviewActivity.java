package com.buildone.dulado.ui.activity.checkout;

        import android.graphics.PorterDuff;
        import android.os.Bundle;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.buildone.dulado.R;
        import com.buildone.dulado.application.AppConstants;
        import com.buildone.dulado.contracts.CheckoutOverviewContract;
        import com.buildone.dulado.parcel.ProductParcel;
        import com.buildone.dulado.ui.activity.BaseActivity;
        import com.bumptech.glide.Glide;

        import java.util.List;

        import javax.inject.Inject;

        import butterknife.BindView;
        import butterknife.BindViews;
        import butterknife.ButterKnife;
        import butterknife.OnClick;
        import dagger.android.AndroidInjection;

public class CheckoutOverviewActivity extends BaseActivity implements CheckoutOverviewContract.View {

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
    @BindView(R.id.btnPurchase)
    Button btnPurchase;
    @BindView(R.id.container_pickup)
    LinearLayout containerPickup;
    @BindView(R.id.container_selected_payment)
    LinearLayout containerSelectedPayment;

    @Inject
    CheckoutOverviewContract.Presenter presenter;

    @BindView(R.id.deliveryTime)
    TextView deliveryTime;
    @BindView(R.id.sellerAddress)
    TextView sellerAddress;
    @BindView(R.id.container_review)
    LinearLayout containerReview;
    @BindView(R.id.confirmOrderProgress)
    ProgressBar confirmOrderProgress;

    @BindViews({R.id.ivCheckCredit, R.id.ivCheckDebitCard, R.id.ivCheckAccDebit, R.id.ivCheckBoleto, R.id.ivCheckMoney})
    List<ImageView> paymentMethodViews;
    @BindView(R.id.tvInsertCoupon)
    TextView tvInsertCoupon;
    @BindView(R.id.etCoupon)
    EditText etCoupon;
    @BindView(R.id.btnConfirmCoupon)
    ImageButton btnConfirmCoupon;
    @BindView(R.id.container_coupon)
    RelativeLayout containerCoupon;

    private ProductParcel product;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_overview);
        product = (ProductParcel) getIntent().getExtras().get(AppConstants.INTENT_TAG_PRODUCT_OBJECT);

        ButterKnife.bind(this);
        AndroidInjection.inject(this);
        presenter.start();

    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.title_activity_checkout_overview));
    }

    @Override
    public void setProductName(String productName) {
        tvProductName.setText(productName);
    }

    @Override
    public void setProductTag(String tags) {
        tvProducTags.setText(tags);
    }

    @Override
    public void setQuantity(int quantity) {
        tvItemCount.setText(String.valueOf(quantity));
    }

    @Override
    public void setDeliverySelected(boolean selected) {
        containerDelivery.setSelected(selected);
        if (selected) {
            ivDelivery.setColorFilter(ContextCompat.getColor(this, R.color.blue_800));
            deliveryText.setTextColor(ContextCompat.getColor(this, R.color.blue_800));
            return;
        }
        ivDelivery.setColorFilter(ContextCompat.getColor(this, R.color.black_title));
        deliveryText.setTextColor(ContextCompat.getColor(this, R.color.black_title));
    }

    @Override
    public void setPickupSelected(boolean selected) {
        containerPickup.setSelected(selected);
        if (selected) {
            ivPickup.setColorFilter(ContextCompat.getColor(this, R.color.blue_800));
            pickupText.setTextColor(ContextCompat.getColor(this, R.color.blue_800));
            return;
        }
        ivPickup.setColorFilter(ContextCompat.getColor(this, R.color.black_title));
        pickupText.setTextColor(ContextCompat.getColor(this, R.color.black_title));
    }

    @Override
    public void setPaymentSelectedStyle(int paymentMethod) {
        paymentMethodViews.get(paymentMethod).setVisibility(View.VISIBLE);
    }

    @Override
    public void openStatus(int status) {
        switch (status) {
            case 1:
                tvOrderStatus.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvPaymentStatus.setVisibility(View.VISIBLE);
                showPaymentButton();
                break;
            case 3:
                deliveryTime.setVisibility(View.VISIBLE);
                sellerAddress.setVisibility(View.VISIBLE);
                break;
            case 4:
                containerReview.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void closeStatus(int status) {
        switch (status) {
            case 0:
                setIncreaseButtonEnabled(false);
                setDecreaseButtonEnabled(false);
                statusDelivery.setEnabled(false);
                statusPaymentMethod.setEnabled(false);
                hidePaymentMethod();
                tvOrderStatus.setText(getString(R.string.label_waiting_accept_order));
                break;
            case 1:
                tvOrderStatus.setText(getString(R.string.label_order_accepted));
                tvOrderStatus.setTextColor(ContextCompat.getColor(this, R.color.blue_800));
                statusOrder.setEnabled(false);
                break;
            case 2:
                tvPaymentStatus.setText(getString(R.string.label_payment_confirmed_status));
                tvPaymentStatus.setTextColor(ContextCompat.getColor(this, R.color.blue_800));
                statusPayment.setEnabled(false);
                hidePaymentButton();
                break;
            case 3:
                statusDelivering.setEnabled(false);
                statusMeetSeller.setEnabled(false);
                break;
        }
    }

    @Override
    public void hidePaymentMethod() {
        containerPaymentMethod.setVisibility(View.GONE);
    }

    @Override
    public void showPaymentSelected() {
        containerSelectedPayment.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPaymentButton() {
        btnPurchase.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePaymentButton() {
        btnPurchase.setVisibility(View.GONE);
    }

    @Override
    public void loadMap() {
        Glide.with(this).load("").centerCrop().into(mapView);
    }

    @Override
    public void setIncreaseButtonEnabled(boolean enable) {
        btnIncrease.setEnabled(enable);
        if (enable) {
            btnIncrease.setColorFilter(ContextCompat.getColor(this, R.color.product_price_text_color));
            return;
        }

        btnIncrease.setColorFilter(ContextCompat.getColor(this, R.color.grey_300));
    }

    @Override
    public void setDecreaseButtonEnabled(boolean enable) {
        btnDecrease.setEnabled(enable);
        if (enable) {
            btnDecrease.setColorFilter(ContextCompat.getColor(this, R.color.product_price_text_color));
            return;
        }

        btnDecrease.setColorFilter(ContextCompat.getColor(this, R.color.grey_300));
    }

    @Override
    public void showSecurePaymentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_finishing_payment, null);
        builder.setView(view);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.blue_900), PorterDuff.Mode.SRC_IN);
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void hideSecurePaymentDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void setConfirmOrderProgressVisible(boolean visible) {
        confirmOrderProgress.setVisibility(visible ? View.VISIBLE : View.GONE);
        confirmOrderProgress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.blue_900), PorterDuff.Mode.SRC_IN);

    }

    @Override
    public void uncheckAllPaymentMethods() {
        ButterKnife.apply(paymentMethodViews, SELECTED, false);
    }

    @Override
    public void showCouponEditText() {
        btnCupom.setVisibility(View.GONE);
        tvInsertCoupon.setVisibility(View.GONE);
        etCoupon.setVisibility(View.VISIBLE);
        btnConfirmCoupon.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCouponContainer() {
        containerCoupon.setVisibility(View.GONE);
    }

    @OnClick({R.id.btnDecrease,
            R.id.btnIncrease,
            R.id.btnCupom,
            R.id.btnDelivery,
            R.id.btnPickup,
            R.id.btnConfirm,
            R.id.btnLike,
            R.id.btnPurchase,
            R.id.btnConfirmCoupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDecrease:
                presenter.decreaseQuantity();
                break;
            case R.id.btnIncrease:
                presenter.increaseQuantity();
                break;
            case R.id.btnCupom:
                presenter.insertCupon();
                break;
            case R.id.btnDelivery:
                presenter.selectDelivery();
                break;
            case R.id.btnPickup:
                presenter.selectPickup();
                break;
            case R.id.btnConfirm:
                presenter.confirmOrder();
                break;
            case R.id.btnLike:
                presenter.reviewProduct();
                break;
            case R.id.btnPurchase:
                presenter.attemptToPurchase();
                break;
            case R.id.btnConfirmCoupon:
                break;
        }
    }


    public ProductParcel getProductParcel() {
        return product;
    }

    static final ButterKnife.Setter<View, Boolean> SELECTED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(View view, Boolean value, int index) {
            view.setVisibility(value ? View.VISIBLE : View.GONE);
        }
    };

    @OnClick({R.id.container_credit_card, R.id.container_debit_card, R.id.container_account_debit, R.id.container_boleto, R.id.container_money})
    public void onPaymentTouched(View view){
        switch (view.getId()){
            case R.id.container_credit_card:
                presenter.selectPaymentMethod(0);
                break;
            case R.id.container_debit_card:
                presenter.selectPaymentMethod(1);
                break;
            case R.id.container_account_debit:
                presenter.selectPaymentMethod(2);
                break;
            case R.id.container_boleto:
                presenter.selectPaymentMethod(3);
                break;
            case R.id.container_money:
                presenter.selectPaymentMethod(4);
                break;
        }
    }

}
