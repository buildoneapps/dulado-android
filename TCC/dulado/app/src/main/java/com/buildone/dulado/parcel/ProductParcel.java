package com.buildone.dulado.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import com.buildone.dulado.model.ProductObject;

/**
 * Created by Alessandro Pryds on 05/05/2017.
 */

public class ProductParcel implements Parcelable {
    private int id;
    private String name;
    private float price;

    public ProductParcel(ProductObject product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public ProductObject getProduct(){
        return new ProductObject(id,0,name,price);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeFloat(this.price);
    }

    protected ProductParcel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.price = in.readFloat();
    }

    public static final Parcelable.Creator<ProductParcel> CREATOR = new Parcelable.Creator<ProductParcel>() {
        @Override
        public ProductParcel createFromParcel(Parcel source) {
            return new ProductParcel(source);
        }

        @Override
        public ProductParcel[] newArray(int size) {
            return new ProductParcel[size];
        }
    };
}
