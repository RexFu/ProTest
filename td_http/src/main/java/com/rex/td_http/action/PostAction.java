package com.rex.td_http.action;

import android.util.Log;

import java.util.Map;

/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */

public class PostAction  extends BaseAction {
    @Override
    protected String getUrl() {
        return mUrl;
    }

    @Override
    protected int getHttpMethod() {
        return Method_POST;
    }

    @Override
    protected Map<String, String> getParms() {
        return params;
    }

    @Override
    protected boolean getTargetDataFromJson(String aResult, long aTaskId) {
       listener.onSuccess(aResult,aTaskId);
        return true;
    }
}
