package com.rex.td_http.action;
import android.util.Log;


import java.util.Map;
/**
 * Created by Rex.wang on 2017/4/8
 * E-mail:WWF1116@163.com
 */

public class GetAction extends BaseAction {

    @Override
    protected String getUrl() {
        return mUrl;
    }
    @Override
    protected int getHttpMethod() {
        return Method_GET;
    }
    @Override
    protected Map<String, String> getParms() {
        return params;
    }
    @Override
    protected boolean getTargetDataFromJson(String aResult, long aTaskId) {
        Log.e("flag--","GetAction--getTargetDataFromJson--26--"+aTaskId);
            listener.onSuccess(aResult,aTaskId);
        return true;
    }
}
