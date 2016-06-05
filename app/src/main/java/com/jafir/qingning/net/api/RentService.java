package com.jafir.qingning.net.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * 关于租车模块的API
 * Created by jafir on 16/5/23.
 */
public interface RentService {

    @GET("/rent/getShopList")
    Call<ResponseBody> getShopList(
            @Query("cityId") String cid);

    @GET("/rent/getShopList")
    Observable<ResponseBody> getShopListRx(
            @Query("cityId") String cid);

    @GET("/rent/getShopList")
    Call<ResponseBody> getShopList(
            @Query("userId") String uid,
            @Query("cityId") String cid);

    @GET("/rent/getShopList")
    Observable<ResponseBody> getShopListRx(
            @Query("userId") String uid,
            @Query("cityId") String cid);

    @GET("/rent/submitOrder")
    Call<ResponseBody> submitOrder(
            @Query("shopId") String shopId,
            @Query("data") String data);

    @GET("/rent/submitOrder")
    Observable<ResponseBody> submitOrderRx(
            @Query("shopId") String shopId,

            @Query("data") String data);

    @GET("/rent/getShopInfo")
    Call<ResponseBody> getShopInfo(
            @Query("shopId") String shopId);

    @GET("/rent/getShopInfo")
    Observable<ResponseBody> getShopInfoRx(
            @Query("shopId") String shopId);

    @GET("/rent/getOrderInfo")
    Call<ResponseBody> getOrderInfo(
            @Query("orderId") String orderId,
            @Query("userId") String uid);

    @GET("/rent/getOrderInfo")
    Observable<ResponseBody> getOrderInfoRx(
            @Query("orderId") String orderId,
            @Query("userId") String uid);

    @GET("/rent/getCommmentList")
    Call<ResponseBody> getCommmentList(
            @Query("shopId") String shopId,
            @Query("pageIndex") String pageIndex,
            @Query("pageSize") String pageSize);

    @GET("/rent/getCommmentList")
    Observable<ResponseBody> getCommmentListRx(
            @Query("shopId") String shopId,
            @Query("pageIndex") String pageIndex,
            @Query("pageSize") String pageSize);

    @GET("/rent/getBicycleList")
    Call<ResponseBody> getBicycleList(
            @Query("shopId") String shopId);

    @GET("/rent/getBicycleList")
    Observable<ResponseBody> getBicycleListRx(
            @Query("shopId") String shopId);


}
