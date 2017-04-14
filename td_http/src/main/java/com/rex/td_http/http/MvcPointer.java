package com.rex.td_http.http;

import android.content.Context;

import com.rex.td_http.action.TbAction;
import com.rex.td_http.listener.HttpProxy;

/**
 * Created by CysionLiu on 2017/4/7.
 * the entrance
 */
public class MvcPointer {
    private static Context mContext;
    private static HttpProxy mHttpProxy;

    /**
     * Created by Rex.wang on 2017/4/12
     * E-mail:WWF1116@163.com
     */
    public static void init(Context aContext, boolean aIsDebug, HttpProxy aHttpProxy) {
        if (aContext == null) {
            try {
                throw new Exception("aContext should not be null");
            } catch (Exception aE) {
                aE.printStackTrace();
            }
        }
        mContext = aContext.getApplicationContext();
        TbAction.initAction(mContext, aIsDebug);
        mHttpProxy = aHttpProxy;
        if (mHttpProxy == null || aContext == null) {
//            mHttpProxy = RetrofitProxy.getInstance(mContext);
            mHttpProxy=VolleyProxy.getInstance(mContext);
        }
    }

    /**
     * 获得网络相关的代理者
     * @return 网络加载代理对象
     */
    public static HttpProxy getHttpProxy() {
        return mHttpProxy;
    }

    public static void clearCache(Context aContext) {
        CacheProxy.get(aContext).clear();
    }
}
