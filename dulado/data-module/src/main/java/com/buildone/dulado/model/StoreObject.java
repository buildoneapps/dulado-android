package com.buildone.dulado.model;

import java.util.ArrayList;

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
        return new ProductObject(1,id,12.2f,"teste","Teste descricao", new ArrayList<String>(){{add("https://s-media-cache-ak0.pinimg.com/736x/b1/2a/74/b12a74b433c345e8c77131c1681e7b83.jpg");}});
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
