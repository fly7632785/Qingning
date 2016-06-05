package com.jafir.qingning.model.entity;

/**
 * Created by jafir on 16/6/2.
 */
public class RegitsterEntity {

    /**
     * flag : true
     * message : 恭喜您,注册成功!
     */



    private boolean flag;
    private String message;


    @Override
    public String toString() {
        return "RegitsterEntity{" +
                "flag=" + flag +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
