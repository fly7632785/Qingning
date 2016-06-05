package com.jafir.qingning.net.impl;

import com.jafir.qingning.app.AppContext;
import com.jafir.qingning.net.api.ApiService;
import com.jafir.qingning.net.api.EventService;
import com.jafir.qingning.net.api.GuideBookService;
import com.jafir.qingning.net.api.RentService;

import org.kymjs.kjframe.utils.SystemTool;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 网络请求封装调用类
 * Created by jafir on 16/5/31.
 */
public class MyHttpClient {
    private String baseUrl = "http://qn.xxblog.cn/api/";
    private static MyHttpClient mInstance = null;

    public MyHttpClient() {
    }

    public static synchronized MyHttpClient getInstance() {
        if (mInstance == null) {
            mInstance = new MyHttpClient();
        }
        return mInstance;

    }


    /**
     * 配置自己的缓存策略
     * 有网则去请求
     * 无网则从缓存拿
     */
    final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (SystemTool.checkNet(AppContext.context)) {
                int maxAge = 0 * 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    //setup cache
    File httpCacheDirectory = new File(AppContext.context.getCacheDir(), "okhttpCache");
    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    Cache cache = new Cache(httpCacheDirectory, cacheSize);
    OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    ApiService apiService = retrofit.create(ApiService.class);
    RentService rentService = retrofit.create(RentService.class);
    EventService eventService = retrofit.create(EventService.class);
    GuideBookService guideBookService = retrofit.create(GuideBookService.class);

    public ApiService getApiService() {
        return apiService;
    }

    public RentService getRentService() {
        return rentService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public GuideBookService getGuideBookService() {
        return guideBookService;
    }
}
