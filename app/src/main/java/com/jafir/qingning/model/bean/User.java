package com.jafir.qingning.model.bean;


/**
 * Created by PanPan on 16/5/20.
 */
public class User extends BaseBean {


    /**
     * nickName : jfir
     * phone : 13982004324
     * password : null
     * sex : null
     * headImgUrl : http://img1.lukou.com/static/p/avatar/large/0000/02/16/85/21685-2.jpg
     * description : null
     */

    private String nickName;
    private String phone;
    private String password;
    private Boolean sex;
    private String headImgUrl;
    private String description;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
