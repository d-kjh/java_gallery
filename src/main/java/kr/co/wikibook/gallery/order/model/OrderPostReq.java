package kr.co.wikibook.gallery.order.model;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class OrderPostReq {
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private List<Integer> itemIds;
}
