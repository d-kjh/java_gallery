package kr.co.wikibook.gallery.cart.model;

import lombok.Getter;

@Getter
public class CartGetRes {
    private int id;
    private String name;
    private String imgPath;
    private int price;
    private int discountPer;
}
