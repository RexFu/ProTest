package com.rex.td_http.proxy;
import android.content.Context;
import android.text.TextUtils;

import com.rex.td_http.base.DataState;
import com.rex.td_http.base.Td;
import com.rex.td_http.listener.Callback;
import com.rex.td_http.listener.THttpListener;
import com.rex.td_http.utils.Constant;
import com.rex.td_http.utils.TdEncryptUtils;
import com.rex.td_http.utils.TdStateUtils;

import java.util.Map;
import java.util.Set;

import static com.rex.td_http.base.DataState.CACHE_FIRST;
import static com.rex.td_http.base.DataState.HEAD_REFRESH;
import static com.rex.td_http.base.DataState.LOAD_MORE;
import static com.rex.td_http.base.DataState.NET_FIRST;

/**
 * Created by Administrator on 2017/4/10.
 */

public class HttpAction implements THttpListener{
    private final Context mContext;
    private Callback mCallBack;
    //请求网络的方法
    protected static final int Method_GET = 0;
    protected static final int Method_POST = 1;
    private DataState mDataState;
    private int taskId;
    private String key;

    public HttpAction(Context mContext){
        this.mContext=mContext;
    }
    public void setData(String url, int Methed, Class aClass, Map<String, String> aMap, int taskId, DataState aDataState){
        this.mDataState=aDataState;
        this.taskId=taskId;
        if (!TdStateUtils.isNetAvailable(mContext)) {
            whenNoNet();
            return;
        }
        if (mDataState == CACHE_FIRST) {
            if (!isCacheValid(taskId)) {
                byhttp(url, Methed, aClass, aMap, taskId);
            }
        } else {
            byhttp(url, Methed, aClass, aMap, taskId);
        }

    }

    private void byhttp(String url, int Methed, Class aClass, Map<String, String> aMap, int taskId) {
        //判断是否为get请求
        if(Methed==Method_GET){
            if (aMap.size() > 0) {
                url=getAppearUrl(aMap,url);
            }
            Td.getHttpProxy().getHttpData(url,this,aClass,taskId);
        }else {
            Td.getHttpProxy().postHttpData(url,this,aClass,aMap,taskId);
        }
        getCacheKey(url,aMap);
    }

    /**
     * 根据每个Url和参数设置缓存的Key值
     * @param aUrl  请求url
     * @param aMap   连接的参数
     *  通过参数与url连接进行拼接做为Key
     */

    private void getCacheKey(String aUrl, Map<String, String> aMap) {
        String buffer = getAppearUrl(aMap,aUrl);
        try {
            key = TdEncryptUtils.MD5encrypt(buffer.toString(), "utf-8");
        } catch (Exception aE) {
            aE.printStackTrace();
        }
    }

    /**
     * 没有网络时，首先返回错误码，然后对应不同请求有不同方式
     * 对于默认缓存的，当然直接获得目标数据
     * 对于默认联网却也可得到缓存的，也直接返回目标数据
     */
    protected void whenNoNet() {
        mCallBack.onFailure(Constant.NO_NET, taskId);
        switch (mDataState) {
            case CACHE_FIRST:
            case NET_FIRST:
                isCacheValid(taskId);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param aTaskId  根据TaskId读取缓存
     * @return
     */
    private boolean isCacheValid(long aTaskId) {
        String fromCache = readCache(aTaskId);
        if (!TextUtils.isEmpty(fromCache)) {
           // return handleDataFromCache(fromCache, aTaskId);
        }
        return false;
    }
    private String readCache(long aTaskId) {
        if (mDataState == DataState.NO_CACHE) {
            return null;
        }
        CacheProxy cacheObj = CacheProxy.get(mContext);
        String fromCache = cacheObj.getAsString(key);
        return fromCache;

    }

    private void writeToCache(String shouldCache) {
        if (mDataState == DataState.NO_CACHE) {
            return;
        }
        if (TextUtils.isEmpty(shouldCache)) {
            return;
        }
        CacheProxy cacheObj = CacheProxy.get(mContext);
        switch (mDataState) {
            case CACHE_FIRST:
            case NET_FIRST:
                cacheObj.remove(key);
               // cacheObj.put(key, shouldCache,getKeepTime());
                break;
            case HEAD_REFRESH:
               // addHeadCache(shouldCache);
                break;
            case LOAD_MORE:
              //  loadMoreCache(shouldCache);
                break;
        }
    }
    /**
     *在这个方式去监听成功和失败，在这里的倒监听器主要是为了处理缓存
     * @param callback  注册的监听器
     */
    public void execute(Callback callback){
        this.mCallBack=callback;
    }
    /**
     *成功回调的得到成功的内容
     */
    @Override
    public void onSuccess(Object obj, int taskId) {
        mCallBack.onSuccess(obj,taskId);
    }
    @Override
    public void onFailure(Object obj, int taskId) {
        mCallBack.onFailure(obj,taskId);
    }
    private String getAppearUrl(Map<String, String> aParams,String url) {
        String temp = url + "?";
        Set<String> strings = aParams.keySet();
        for (String aKey : strings) {
            temp = temp + aKey + "=" + aParams.get(aKey) + "&";
        }
        temp = temp.substring(0, temp.length() - 1);

        return temp;
    }
}
