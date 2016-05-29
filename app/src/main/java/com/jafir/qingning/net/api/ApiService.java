package com.jafir.qingning.net.api;

import com.jafir.qingning.model.bean.Chehang;
import com.jafir.qingning.model.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jafir on 16/5/26.
 */
public interface ApiService {

    @GET("/rent/getShopList")
    Call <List<Chehang>> listShop(@Query("userid") String uid);



    //登录，获取token

    @GET("/login")
    Observable<String> login(
            @Query("username") String username,
            @Query("password") String password);
    //根据token获取用户信息
    @GET("/user")
    public Observable<User> getUser(
            @Query("token") String token);


    //动态添加header
    @GET("user")
    Call<User> getUsers(@Header("Authorization") String authorization);


    /**
     * 注意 /user  和 user的区别
     * baseUrl http://api.demo.come/base/home/
     * /user 是相对路径是域名之后就拼接  比如 最后的url是http://api.demo.come/user
     * user 是直接在后面拼接 比如 最后的url是http://api.demo.come/base/home/user
     */

//    login("11111", "22222")
//    .flatMap(new Func1<String, Observable<User>>() {  //得到token后获取用户信息
//        @Override
//        public Observable<User> onNext(String token) {
//            return getUser(token);
//        })
//        .subscribeOn(Schedulers.newThread())//请求在新的线程中执行请求
//                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
//                .doOnNext(new Action1<UserInfo>() {      //保存用户信息到本地
//                    @Override
//                    public void call(UserInfo userInfo) {
//                        saveUserInfo(userInfo);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onNext(User user) {
//                        //完成一次完整的登录请求
//                        userView.setUser(user);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable error) {
//                        //请求失败
//                    }
//                });
}
