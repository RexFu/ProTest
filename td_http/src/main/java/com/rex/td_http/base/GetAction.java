package com.rex.td_http.base;

import android.util.Log;

/**
 * Created by Administrator on 2017/4/12.
 */

public class GetAction extends MvcAction {
    private String mUrl;

    public GetAction setUrl(String mUrl){
        this.mUrl=mUrl;
        return this;
    }
    @Override
    protected String getUrl() {
        return mUrl;
    }
    @Override
    protected int getHttpMethod() {
        return Method_GET;
    }
    @Override
    protected boolean getTargetDataFromJson(String aResult, long aTaskId) {
        listener.onSuccess(aResult,aTaskId);

        return true;

    }
    @Override
    protected int getKeepTime() {
        return 10;
    }
}
