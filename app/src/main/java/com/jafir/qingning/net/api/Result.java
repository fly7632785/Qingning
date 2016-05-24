package com.jafir.qingning.net.api;

/**
 * Created by jafir on 16/5/23.
 */
public class Result<T> {
    public int code;
    public String message;
    //利用泛型  来指定不确定的 数据类型
//    {"code":"0","message":"success","data":{}}
//    {"code":"0","message":"success","data":[]}
    public String  data;


    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
