package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 08/10/2017.
 */

public class PendingSaleObject {
    private String productName;
    private String imageUrl;
    private BuyerModel buyer;
    private float price;
    private String tag;
    private int id;

    public PendingSaleObject() {
    }

    public PendingSaleObject(String productName, String imageUrl, float price) {
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BuyerModel getBuyer() {
        return buyer;
    }

    public float getPrice() {
        return price;
    }

    public String getTag() {
        return tag;
    }

    public int getId() {
        return id;
    }

    public static PendingSaleObject createTest(){
        return  new PendingSaleObject("Bala de chocolate","http://imagens.gimba.com.br/objetosmidia/ExibirObjetoMidia?Id=15422",0.10f);
    }

}
