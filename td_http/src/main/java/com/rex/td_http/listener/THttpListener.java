package com.rex.td_http.listener;

/**
 * Created by Rex on 2016/6/16 0016.
 * to get light and fluent data from net request
 */
public interface THttpListener {
    /**
     * 成功获得结果返回
     * @param obj
     * @param taskId
     */
    void onSuccess(Object obj, int taskId);

    /**
     * 结果获得失败。目前返回的obj是VolleyError
     * @param obj
     * @param taskId
     */
    void onFailure(Object obj, int taskId);
}
