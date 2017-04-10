package com.rex.td_http.proxy;
import com.rex.td_http.base.DataState;
import com.rex.td_http.base.Td;
import com.rex.td_http.listener.Callback;
import com.rex.td_http.listener.THttpListener;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/10.
 */

public class HttpAction implements THttpListener{
    private Callback aCallback;
    private Callback mCallBack;
    //请求网络的方法
    protected static final int Method_GET = 0;
    protected static final int Method_POST = 1;
    public HttpAction(String url, int Methed, Class aClass, Map<String, String> aMap, int taskId, DataState aDataState){
        //判断是否为get请求
        if(Methed==Method_GET){
            if (aMap.size() > 0) {
                url=getAppearUrl(aMap,url);
            }
            Td.getHttpProxy().getHttpData(url,this,aClass,taskId);
        }else {
            Td.getHttpProxy().postHttpData(url,this,aClass,aMap,taskId);
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
