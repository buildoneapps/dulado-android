package com.buildone.dulado.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import com.buildone.dulado.model.ProductObject;

/**
 * Created by Alessandro Pryds on 22/05/2017.
 */

public class ProductParcel implements Parcelable {
    private int id;
    private int storeId;
    private String name;
    private float price;

    public ProductParcel(ProductObject productObject) {
        this.id = productObject.getId();
        this.storeId = productObject.getStoreId();
        this.name = productObject.getName();
        this.price = productObject.getPrice();
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
        dest.writeInt(this.storeId);
        dest.writeString(this.name);
        dest.writeFloat(this.price);
    }

    protected ProductParcel(Parcel in) {
        this.id = in.readInt();
        this.storeId = in.readInt();
        this.name = in.readString();
        this.price = in.readFloat();
    }

    public static final Creator<ProductParcel> CREATOR = new Creator<ProductParcel>() {
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
