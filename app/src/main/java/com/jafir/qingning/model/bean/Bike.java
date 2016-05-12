package com.jafir.qingning.model.bean;

import java.io.Serializable;

/**
 * Created by jafir on 16/5/12.
 */
public class Bike implements Serializable{


    private String name;
    private String price;
    private int orderCount;
    private int totalCount;
    private int spareCount;
    private String kind;
    private String img;


    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", orderCount=" + orderCount +
                ", totalCount=" + totalCount +
                ", spareCount=" + spareCount +
                ", kind='" + kind + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSpareCount() {
        return spareCount;
    }

    public void setSpareCount(int spareCount) {
        this.spareCount = spareCount;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
