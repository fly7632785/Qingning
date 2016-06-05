package com.jafir.qingning.model.bean;

/**
 * Created by PanPan on 16/5/20.
 */
public class BicycleShop extends BaseBean {

    private String name;

    private String address;

    private String coverImgUrl;

    private String phone;

    private String openTime;

    private Float score;

    private Integer scoreNum;

    private Integer rentNum;

    private Double latitude;

    private Double longitude;

    private Long cityId;

    private String discountInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    public Integer getRentNum() {
        return rentNum;
    }

    public void setRentNum(Integer rentNum) {
        this.rentNum = rentNum;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(String discountInfo) {
        this.discountInfo = discountInfo;
    }

    @Override
    public String toString() {
        return "BicycleShop{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", openTime='" + openTime + '\'' +
                ", score=" + score +
                ", scoreNum=" + scoreNum +
                ", rentNum=" + rentNum +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cityId=" + cityId +
                ", discountInfo='" + discountInfo + '\'' +
                '}';
    }
}
