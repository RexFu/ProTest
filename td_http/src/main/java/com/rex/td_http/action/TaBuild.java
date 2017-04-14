package com.rex.td_http.action;

import com.rex.td_http.config.DataState;
import com.rex.td_http.listener.CallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rex.wang on 2017/4/14
 * E-mail:WWF1116@163.com
 */

public abstract class TaBuild <T extends TaBuild> {
    protected String mUrl;
    protected Map<String, String> params;
    protected CallBack listener;
    protected int taskId;

    /*传入Url*/
    public T setUrl(String mUrl){
        this.mUrl=mUrl;
        return (T) this;
    }
    /*给get请求设置参数 */
    public T params(Map<String, String> aParams) {
        this.params = aParams;
        return (T) this;
    }
    /*给get请求设置参数 */
    public T addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new HashMap<>();
        }
        params.put(key, val);
        return (T) this;
    }
    /*给请求设置任务id */
    public T taskId(int id) {
        taskId = id;
        return (T) this;
    }
    public abstract T build(DataState actionCode);
    public T execute(CallBack mListener){
        this.listener=mListener;
        return (T) this;
        
    };
}
