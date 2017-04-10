package com.rex.td_http.proxy;
import com.rex.td_http.base.DataState;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/7.
 */

public class HttpApi {
    private String mUrl;
    private Map<String, String> mParms;
    private int Method;
    private int mId;
    private Map<String, String> params;
    private Class taget;
    private DataState mDataState;

    public HttpApi(int aI) {
        this.Method=aI;
    }

    public HttpApi setUrl(String aUrl){
        this.mUrl=aUrl;
        return this;
    }
    public HttpApi params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }
    public HttpApi setParms(String key,String val){
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }
    public HttpApi Clazz(Class taget){
        this.taget=taget;
        return this;
    }
    public HttpApi Mode(DataState mDataState){
        this.mDataState=mDataState;
        return this;
    }
    public HttpApi taskId(int id){
        this.mId=id;
        return this;
    }
    public HttpAction build(){
        return new HttpAction(mUrl,Method,taget,params,mId,mDataState);
    }
}
