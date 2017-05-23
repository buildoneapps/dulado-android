package com.buildone.logic.interactor;

import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.SearchObject;
import com.buildone.dulado.model.SellerObject;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Alessandro Pryds on 05/05/2017.
 */

public class ProductInteractor implements IProductInteractor {
    @Override
    public Observable<ArrayList<SearchObject>> getProducts() {
        ArrayList<SearchObject> objects = new ArrayList<>();
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s640x640/sh0.08/e35/18580827_428197184230017_882390074501627904_n.jpg",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18580362_1551180898254755_7834395302843908096_n.jpg",new SellerObject(),0.25f,"#moletom #conforto #despojado #trend #trendy"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18513178_313944249039563_1716682931969196032_n.jpg",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18579553_285448965243351_2083154778891747328_n.jpg",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18513065_1686192668353125_6282040379420180480_n.jpg",new SellerObject(),0.25f,"doce"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18580064_432035667167081_7957693278357291008_n.jpg",new SellerObject(),0.25f,"#tenis"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18513746_279977939130984_8603875209734258688_n.jpg",new SellerObject(),0.25f,"#spray #shampoo #temQter"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s640x640/sh0.08/e35/18581196_292493837857997_4986453848085233664_n.jpg",new SellerObject(),0.25f,"#oculos #french"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/17933911_124968398058656_8698911248172974080_n.jpg",new SellerObject(),0.25f,"#colar #pingente"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s750x750/sh0.08/e35/18644806_247549965722065_8052362002602917888_n.jpg",new SellerObject(),0.25f,"#bolsa "));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s750x750/sh0.08/e35/18512725_1778767159081839_321805316620550144_n.jpg",new SellerObject(),0.25f,"#nerd #pantufa #zumbi"));
        objects.add(new SearchObject(0,"https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s750x750/sh0.08/e35/18512654_1347226421997221_5058279750112378880_n.jpg",new SellerObject(),0.25f,"#nerd #livro"));
        return Observable.fromArray(objects);
    }
}
