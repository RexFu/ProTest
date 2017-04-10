package com.rex.td_http.base;

import android.content.Context;

import com.rex.td_http.proxy.HttpProxy;
import com.rex.td_http.proxy.ParserProxy;

/**
 * Created by Rex on 2017/4/7.
 */

public class Td {
    public static void init(Context aContext){
        Context mAppContext = aContext.getApplicationContext();
        HttpProxy.getQueue(mAppContext);

    }
    /**
     * 获得解析json字符串相关的代理者
     *
     * @return json解析者代理对象
     */
    public static ParserProxy getParseProxy() {
        return ParserProxy.getInstance();
    }
    /**
     * 获得网络相关的代理者
     *
     * @return 网络加载代理对象
     */
    public static HttpProxy getHttpProxy() {
        return HttpProxy.getInstance();
    }

}
