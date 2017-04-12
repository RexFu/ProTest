package com.rex.td_http.base;

/**
 * Created by Administrator on 2017/4/12.
 */

public class HttpUtils {
    public static GetAction get(){
        return new GetAction();
    }
    public static PostAction post(){
        return new PostAction();
    }
}
