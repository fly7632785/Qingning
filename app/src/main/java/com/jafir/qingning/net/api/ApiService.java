package com.jafir.qingning.net.api;

import com.jafir.qingning.model.entity.LoginEntity;
import com.jafir.qingning.model.entity.RegitsterEntity;
import com.jafir.qingning.model.bean.Result;
import com.jafir.qingning.model.bean.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 主要是公共api
 * Created by jafir on 16/5/26.
 */
public interface ApiService {


    //登录，获取token
    @FormUrlEncoded
    @POST("user/login")
    Observable<Result<LoginEntity>> loginRx(
            @Field("phone") String username,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseBody> login(
            @Field("phone") String username,
            @Field("password") String password);


    //注册
    @FormUrlEncoded
    @POST("user/register")
    Call<ResponseBody> register(
            @Field("phone") String username,
            @Field("nickName") String nickname,
            @Field("password") String password,
            @Field("headImgUrl") String url);

    @FormUrlEncoded
    @POST("user/register")
    Observable<Result<RegitsterEntity>> registerRx(
            @Field("phone") String username,
            @Field("nickName") String nickname,
            @Field("password") String password
            );


    /**
     * 这里的 post + query 参数会在url中构造，而上面的不会，但是都是会被抓包工具抓出明文
     * 所以之后还要加密验证
     * 一般用上面的方法
     *
     * @param username
     * @param nickname
     * @param url
     * @param password
     * @return
     */
    @POST("user/register")
    Call<ResponseBody> register1(
            @Query("phone") String username,
            @Query("nickName") String nickname,
            @Query("headImgUrl") String url,
            @Query("password") String password);


    //根据token获取用户信息
    @GET("/user")
    Observable<User> getUser(
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
