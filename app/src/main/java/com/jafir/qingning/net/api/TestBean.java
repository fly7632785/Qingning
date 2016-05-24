package com.jafir.qingning.net.api;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by jafir on 16/5/24.
 */
public class TestBean {

    @Expose
    private String name;
    private long time;
    @Expose (serialize = true,deserialize = false)
    private int age;
    @Expose(serialize = false,deserialize = true)
    private String email;

    private Date date;


    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }


    private class MyTypeAdapter extends TypeAdapter<TestBean>{

        @Override
        public void write(JsonWriter out, TestBean value) throws IOException {
            out.beginObject();
            out.name("name").value(value.name);
            out.name("email").value(value.email);
            out.name("age").value(value.age);
            out.endObject();
        }

        @Override
        public TestBean read(JsonReader in) throws IOException {

            TestBean bean = new TestBean();
            in.beginObject();
            while (in.hasNext()){
                switch (in.nextName()){
                    case "name":
                        bean.setName(in.nextString());
                        break;
                    case "age":
                        bean.setAge(in.nextInt());
                        break;
                    case "email":
                        bean.setEmail(in.nextString());
                        break;
                }
            }
            in.endObject();
            return bean;
        }
    }
}
