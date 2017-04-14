package com.rex.td_http.listener;

import java.util.Map;

/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */
public interface HttpProxy {

    void getData(String url, final THttpListener callBack, final Map<String, String> paraMap, final Map<String,
            String> headers, final long taskId);

    void postData(String url, final THttpListener callBack,
                  final Map<String, String> paraMap, final Map<String,
            String> headers, final long taskId);
}
