package com.jafir.qingning.model.bean;

/**
 * Created by jafir on 16/7/19.
 */
public class ContactPerson {

    private String name ;
    private String phone ;


    @Override
    public String toString() {
        return "ContactPerson{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
