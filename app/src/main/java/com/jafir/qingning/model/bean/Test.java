package com.jafir.qingning.model.bean;

import java.util.List;

/**
 * Created by jafir on 2017/4/10.
 */

public class Test
{
    /**
     * name :
     * img :
     * desc :
     * location :
     * locationInfo :
     * phone :
     * opentime :
     * score :
     * discountInfo :
     * distance :
     * zuci :
     * comments : [{"imgUrl":"","name":"","time":"","content":""}]
     */

    private String name;
    private String img;
    private String desc;
    private String location;
    private String locationInfo;
    private String phone;
    private String opentime;
    private String score;
    private String discountInfo;
    private String distance;
    private String zuci;
    private List<CommentsBean> comments;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(String discountInfo) {
        this.discountInfo = discountInfo;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getZuci() {
        return zuci;
    }

    public void setZuci(String zuci) {
        this.zuci = zuci;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * imgUrl :
         * name :
         * time :
         * content :
         */

        private String imgUrl;
        private String name;
        private String time;
        private String content;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
