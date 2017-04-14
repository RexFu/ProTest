package com.rex.td_http.action;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rex.wang on 2017/4/14
 * E-mail:WWF1116@163.com
 */

public abstract class BaseAction extends TbAction {
    @Override
    protected Map<String, String> getHeader() {
        Map<String,String> headers = new HashMap<>();
        headers.put("test1","one");
        headers.put("test2","two");
        return headers;
    }

}
