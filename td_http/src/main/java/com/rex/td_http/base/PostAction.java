package com.rex.td_http.base;

/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */

public class PostAction  extends MvcAction {
    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected int getHttpMethod() {
        return Method_POST;
    }

    @Override
    protected boolean getTargetDataFromJson(String aResult, long aTaskId) {
        return false;
    }
}
