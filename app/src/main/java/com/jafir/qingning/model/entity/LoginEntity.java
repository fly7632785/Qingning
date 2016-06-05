package com.jafir.qingning.model.entity;

import com.jafir.qingning.model.bean.User;

/**
 * Created by jafir on 16/6/2.
 */
public class LoginEntity {

    private User user;

    @Override
    public String toString() {
        return "LoginEntity{" +
                "data=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
