package com.rex.td_http.proxy;
import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.rex.td_http.base.Td;
import com.rex.td_http.listener.THttpListener;
import java.util.Map;
/**
 * 频繁网络交互的代理者，目前基于volley
 * get和post方法，还包括加入header的情况
 */
public class
HttpProxy {
    private static final String WRONG_SERVER = "连接有问题";
    private static RequestQueue queue;
    private static HttpProxy instance=new HttpProxy();
    public static synchronized HttpProxy getInstance() {return instance;}
    //单例模式创建请求队列，context应传进程的context
    public static synchronized RequestQueue getQueue(Context context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }
        return queue;
    }
    /**
     * get请求,返回网络请求结果，可直接获得对应类型
     *
     * @param url         网络地址
     * @param callBack    结果回调
     * @param taskId      任务ID同时也是该任务的TAG
     * @param targetClass 需要得到的对象
     */
    public void getHttpData(String url, final THttpListener callBack, final Class<?> targetClass, final int taskId) {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                if (!isGoodJson(response)) {
                    callBack.onFailure(new VolleyError(WRONG_SERVER), taskId);
                    return;
                }
                if (targetClass != null) {
                    Object obj = getEntity(response, targetClass);
                    callBack.onSuccess(obj, taskId);
                } else {
                    callBack.onSuccess(response, taskId);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailure(error, taskId);
            }
        }
        );
        stringRequest.setTag(taskId);
        queue.add(stringRequest);
    }
    /**
     * post网络获得结果
     *
     * @param url         请求地址
     * @param callBack    请求回调
     * @param paraMap     参数集合
     * @param taskId      任务id
     * @param targetClass 需要得到的对象
     */
    public void postHttpData(String url, final THttpListener callBack, final Class<?> targetClass,
                             final Map<String, String> paraMap, final int taskId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response
                .Listener<String>() {
            @Override
            public void onResponse(final String response) {
                if (!isGoodJson(response)) {
                    callBack.onFailure(new VolleyError(WRONG_SERVER), taskId);
                    return;
                }
                if (targetClass != null) {
                    Object obj = getEntity(response, targetClass);
                    callBack.onSuccess(obj, taskId);
                } else {
                    callBack.onSuccess(response, taskId);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailure(error, taskId);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paraMap;
            }
        };
        stringRequest.setTag(taskId);
        queue.add(stringRequest);
    }

    /**
     *
     * @param result 成功返回的Json数据
     * @param aClass 需要解析的javaBean
     * @return 返回想要的类型
     */
    private Object getEntity(String result, Class<?> aClass) {
        if (aClass == null) {
            return result;
        }
        return Td.getParseProxy().parse(result, aClass);
    }
    public static boolean isGoodJson(String json) {
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            System.out.println("bad json: " + json);
            return false;
        }
    }
}
