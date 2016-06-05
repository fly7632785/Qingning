package com.jafir.qingning.model.bean;

/**
 * Created by PanPan on 16/5/20.
 */
public class Bicycle extends BaseBean {

    private String name;

    private Float price;

    private Float refPrice;

    private Integer totalNum;

    private Integer spareNum;

    private String bicycleImgUrl;

    private Long bicycleShopId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRefPrice() {
        return refPrice;
    }

    public void setRefPrice(Float refPrice) {
        this.refPrice = refPrice;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getSpareNum() {
        return spareNum;
    }

    public void setSpareNum(Integer spareNum) {
        this.spareNum = spareNum;
    }

    public String getBicycleImgUrl() {
        return bicycleImgUrl;
    }

    public void setBicycleImgUrl(String bicycleImgUrl) {
        this.bicycleImgUrl = bicycleImgUrl;
    }

    public Long getBicycleShopId() {
        return bicycleShopId;
    }

    public void setBicycleShopId(Long bicycleShopId) {
        this.bicycleShopId = bicycleShopId;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", refPrice=" + refPrice +
                ", totalNum=" + totalNum +
                ", spareNum=" + spareNum +
                ", bicycleImgUrl='" + bicycleImgUrl + '\'' +
                ", bicycleShopId=" + bicycleShopId +
                '}';
    }
}
