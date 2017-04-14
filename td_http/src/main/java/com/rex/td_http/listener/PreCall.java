package com.rex.td_http.listener;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */

public interface PreCall {
    @GET
    Call<String> getResult(@Url String url, @HeaderMap Map<String, String> headers);

    @POST
    Call<String> postResult(@Url String url, @HeaderMap Map<String, String> headers, @QueryMap Map<String, String> params);
}
