package com.jafir.qingning.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jafir on 16/5/10.
 */
public class Chehang {

    @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
    private String name;
    private String img;
    private String desc;
    private String distance;
    private String zuci;

    @Override
    public String toString() {
        return "Chehang{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", desc='" + desc + '\'' +
                ", distance='" + distance + '\'' +
                ", zuci='" + zuci + '\'' +
                '}';
    }

    public String getZuci() {
        return zuci;
    }

    public void setZuci(String zuci) {
        this.zuci = zuci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
