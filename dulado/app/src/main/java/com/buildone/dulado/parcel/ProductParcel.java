package com.buildone.dulado.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.model.SellerObject;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 22/05/2017.
 */

public class ProductParcel implements Parcelable {
    private int id;
    private int storeId;
    private String name;
    private float price;
    private String description;
    private ArrayList<String> productImages;

    public ProductParcel(ProductObject productObject) {
        this.id = productObject.getId();
        this.storeId = productObject.getStoreId();
        this.name = productObject.getName();
        this.price = productObject.getPrice();
        this.description = productObject.getDescription();
        this.productImages = productObject.getProductImages();
    }


    public ProductObject getProduct(){
        return new ProductObject(id,storeId,price,name,description,productImages, new SellerObject(1, "https://img.elo7.com.br/users/picture/186E8.jpg?59791763"), "#doce #chocolate", 10);
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
        dest.writeString(this.description);
        dest.writeStringList(this.productImages);
    }

    protected ProductParcel(Parcel in) {
        this.id = in.readInt();
        this.storeId = in.readInt();
        this.name = in.readString();
        this.price = in.readFloat();
        this.description = in.readString();
        this.productImages = in.createStringArrayList();
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
