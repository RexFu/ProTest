package com.rex.td_http.http;

import com.rex.td_http.action.GetAction;
import com.rex.td_http.action.PostAction;

/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */

public class HttpUtils {
    public static GetAction get(){
        return new GetAction();
    }
    public static PostAction post(){
        return new PostAction();
    }
}
