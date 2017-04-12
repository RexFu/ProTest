package com.rex.td_http.base;

/**
 * Created by Administrator on 2017/4/12.
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
