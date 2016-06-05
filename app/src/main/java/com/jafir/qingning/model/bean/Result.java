package com.jafir.qingning.model.bean;

/**
 * Created by jafir on 16/6/2.
 */
public class Result<T> {


    /**
     * message : Successful
     * rcode : 1000
     * data : {"flag":true,"message":"恭喜您,注册成功!"}
     * rescode : PASS_OK
     */

    private String message;
    private int rcode;
    /**
     * flag : true
     * message : 恭喜您,注册成功!
     */

    private T data;
    private String rescode;


    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", rcode=" + rcode +
                ", data=" + data +
                ", rescode='" + rescode + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

}
