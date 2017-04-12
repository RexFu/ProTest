package com.rex.td_http.base;

/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */
public interface THttpListener {

    /**
     * 成功获得结果返回
     * @param obj
     * @param taskId
     */
    void onSuccess(Object obj, long taskId);

    /**
     * 结果获得失败。目前返回的obj是VolleyError
     * @param obj
     * @param taskId
     */
    void onFailure(Object obj, long taskId);
}
