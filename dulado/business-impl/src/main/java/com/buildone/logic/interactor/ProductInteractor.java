package com.buildone.logic.interactor;

import com.buildone.dulado.interactor.IProductInteractor;
import com.buildone.dulado.model.ProductObject;
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
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s640x640/sh0.08/e35/18580827_428197184230017_882390074501627904_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "doce"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18580362_1551180898254755_7834395302843908096_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#moletom #conforto #despojado #trend #trendy"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18513178_313944249039563_1716682931969196032_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "doce"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18579553_285448965243351_2083154778891747328_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "doce"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18513065_1686192668353125_6282040379420180480_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "doce"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18580064_432035667167081_7957693278357291008_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#tenis"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/18513746_279977939130984_8603875209734258688_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#spray #shampoo #temQter"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s640x640/sh0.08/e35/18581196_292493837857997_4986453848085233664_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#oculos #french"));
        objects.add(new SearchObject(1, "Teste Product", "http://www.muitochique.com/wp-content/uploads/2015/12/Naked-Cake-chocolate-1024x830-1024x830-865x701.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "https://s-media-cache-ak0.pinimg.com/736x/b1/2a/74/b12a74b433c345e8c77131c1681e7b83.jpg", new SellerObject(), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "http://www.artesanatoereciclagem.com.br/wp-content/uploads/2017/05/DIY-Ideias-de-artesanato-com-potes-de-vidro-007.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/e35/17933911_124968398058656_8698911248172974080_n.jpg", new SellerObject(), 0.25f, "#colar #pingente"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s750x750/sh0.08/e35/18644806_247549965722065_8052362002602917888_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#bolsa "));
        objects.add(new SearchObject(1, "Teste Product", "http://www.artesanatoereciclagem.com.br/wp-content/uploads/2017/05/DIY-Ideias-de-artesanato-com-potes-de-vidro-004.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s750x750/sh0.08/e35/18512725_1778767159081839_321805316620550144_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #pantufa #zumbi"));
        objects.add(new SearchObject(1, "Teste Product", "https://instagram.fsod2-1.fna.fbcdn.net/t51.2885-15/s750x750/sh0.08/e35/18512654_1347226421997221_5058279750112378880_n.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "https://s-media-cache-ak0.pinimg.com/736x/77/2d/c7/772dc7f9e0e31529723acad12a0b45fe.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "http://3.bp.blogspot.com/-MIF5WJT7GWU/TbxzqJl-_eI/AAAAAAAAAew/ytLuLQSyA-Y/s1600/30042011263.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "https://img.elo7.com.br/product/zoom/F0855B/mix-de-pulseiras-cham-luu-luxo-colar-masculino.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "https://s-media-cache-ak0.pinimg.com/originals/d3/c4/0b/d3c40bf7c14400c51ef02f02e7c49b93.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "https://img.cybercook.uol.com.br/imagens/receitas/593/pao-caseiro.jpg?2017", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        objects.add(new SearchObject(1, "Teste Product", "https://www.rs21.com.br/wp-content/uploads/2013/01/coxinha.jpg", new SellerObject(1,"https://s-media-cache-ak0.pinimg.com/avatars/timsinri_1328981577_280.jpg"), 0.25f, "#nerd #livro"));
        return Observable.fromArray(objects);
    }

    @Override
    public Observable<ProductObject> getProductById(int productId) {
        ArrayList<String> productImages = new ArrayList<String>();
        productImages.add("https://s-media-cache-ak0.pinimg.com/originals/d3/c4/0b/d3c40bf7c14400c51ef02f02e7c49b93.jpg");
        productImages.add("https://s-media-cache-ak0.pinimg.com/originals/d3/c4/0b/d3c40bf7c14400c51ef02f02e7c49b93.jpg");
        productImages.add("https://s-media-cache-ak0.pinimg.com/originals/d3/c4/0b/d3c40bf7c14400c51ef02f02e7c49b93.jpg");
        productImages.add("https://s-media-cache-ak0.pinimg.com/originals/d3/c4/0b/d3c40bf7c14400c51ef02f02e7c49b93.jpg");
        productImages.add("https://s-media-cache-ak0.pinimg.com/originals/d3/c4/0b/d3c40bf7c14400c51ef02f02e7c49b93.jpg");
        return Observable.just(new ProductObject(productId,0,2.5f,"Test Product","Este produto possui uma descrição simples, mas objetiva!", productImages, new SellerObject(1, "https://img.elo7.com.br/users/picture/186E8.jpg?59791763")));
    }
}
