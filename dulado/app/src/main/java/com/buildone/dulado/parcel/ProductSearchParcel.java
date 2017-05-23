package com.buildone.dulado.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.model.SellerObject;

/**
 * Created by Alessandro Pryds on 05/05/2017.
 */

public class ProductSearchParcel implements Parcelable {
    private String imageUrl;
    private int id;
    private String name;
    private float price;
    private int sellerId;
    private String tag;
    public ProductSearchParcel(SearchObject product) {
        this.id = product.getId();
        this.name = product.getProductName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.tag = product.getTag();
        this.sellerId = product.getSeller().getId();
    }

    public SearchObject getSearchObject(){
        return new SearchObject(id, name, imageUrl, new SellerObject(id),price,tag);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeFloat(this.price);
        dest.writeInt(this.sellerId);
        dest.writeString(this.tag);
    }

    protected ProductSearchParcel(Parcel in) {
        this.imageUrl = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.price = in.readFloat();
        this.sellerId = in.readInt();
        this.tag = in.readString();
    }

    public static final Creator<ProductSearchParcel> CREATOR = new Creator<ProductSearchParcel>() {
        @Override
        public ProductSearchParcel createFromParcel(Parcel source) {
            return new ProductSearchParcel(source);
        }

        @Override
        public ProductSearchParcel[] newArray(int size) {
            return new ProductSearchParcel[size];
        }
    };
}
