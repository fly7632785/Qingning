package com.jafir.qingning.net.api;

import com.jafir.qingning.model.bean.Chehang;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by jafir on 16/5/23.
 */
public interface RentService {

    Retrofit retrofit = null;

    @GET("chehang/{chehangID}")
    Call<Result<Chehang>> getChehangList(@Path("chehangID")String id);

    @GET("chel")
    Call<ResponseBody> getResponse(String id);

    



}
