package com.rex.td_http.listener;
/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */
public interface CallBack {

    /**
     * 成功获得结果返回，注意，此结果代表本次请求最希望的数据，即目标数据
     * @param obj
     * @param taskId
     */
    void onSuccess(Object obj, long taskId);

    /**
     * 结果获得失败。可以为缓存带有的结果码
     * @param obj
     * @param taskId
     */
    void onFailure(Object obj, long taskId);
}
