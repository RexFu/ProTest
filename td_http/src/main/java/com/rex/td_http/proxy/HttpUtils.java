package com.rex.td_http.proxy;

/**
 * Created by Administrator on 2017/4/10.
 */

public class HttpUtils {
    public  synchronized static HttpApi get(){
        return new HttpApi(1);
    }
    public  synchronized static HttpApi post(){
        return new HttpApi(2);
    }

}
