package com.rex.td_http;
import android.content.Context;
import com.rex.td_http.base.Constant;
import com.rex.td_http.base.HttpProxy;
import com.rex.td_http.base.PreCall;
import com.rex.td_http.base.THttpListener;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */
public class RetrofitProxy implements HttpProxy {

    private Retrofit mRetrofit;

    private static volatile RetrofitProxy instance;
    private static Context mContext;

    private RetrofitProxy() {
        mRetrofit = new Retrofit.Builder().baseUrl("http://occupy").addConverterFactory(ScalarsConverterFactory.create()).build();
    }

    public static synchronized RetrofitProxy getInstance(Context aContext) {
        if (instance == null) {
            mContext = aContext;
            instance = new RetrofitProxy();
        }
        return instance;
    }

    @Override
    public void getData(String url, final THttpListener callBack, Map<String, String> params, Map<String, String> headers, final long taskId) {
        mRetrofit.create(PreCall.class).getResult(url,headers).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!MvcUtils.isGoodJson(response.body())) {
                    callBack.onFailure(Constant.WRONG_SERVER, taskId);
                    return;
                }
                callBack.onSuccess(response.body(), taskId);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callBack.onFailure(t.getMessage(), taskId);
            }
        });
    }

    @Override
    public void postData(String url, final THttpListener callBack, Map<String, String> params, Map<String, String> headers, final long taskId) {
        mRetrofit.create(PreCall.class).postResult(url,headers,params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!MvcUtils.isGoodJson(response.body())) {
                    callBack.onFailure(Constant.WRONG_SERVER, taskId);
                    return;
                }
                callBack.onSuccess(response.body(), taskId);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callBack.onFailure(t.getMessage(), taskId);
            }
        });
    }
}
