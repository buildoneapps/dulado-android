package com.buildone.dulado.model;

/**
 * Created by Alessandro Pryds on 25/04/2017.
 */

public class StoreObject {
    private String name;
    private int id;
    private int selledId;

    public StoreObject(int id, String name, int selledId) {
        this.name = name;
        this.id = id;
        this.selledId = selledId;
    }

    public ProductObject getProduct(){
        return new ProductObject(1,id,"teste",12.2f);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getSellerId() {
        return selledId;
    }
}
