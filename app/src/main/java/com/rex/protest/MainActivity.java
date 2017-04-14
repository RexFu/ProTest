package com.rex.protest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.rex.td_http.http.MvcPointer;
import com.rex.td_http.http.RetrofitProxy;
import com.rex.td_http.config.DataState;
import com.rex.td_http.http.HttpUtils;
import com.rex.td_http.listener.CallBack;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Rex.wang on 2017/4/12
 * E-mail:WWF1116@163.com
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGet;
    private Button mPost;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MvcPointer.init(this, true, RetrofitProxy.getInstance(this));
        mText=(TextView) findViewById(R.id.tv);
        mGet=(Button) findViewById(R.id.btn_get);
        mPost=(Button) findViewById(R.id.btn_post);
        mGet.setOnClickListener(this);
        mPost.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
              getData();
             break;
            case R.id.btn_post:
                postData();

                break;
        }
    }


    private void postData() {
                Map<String, String> aParams=new HashMap<>();
        aParams.put("scope","103");
        aParams.put("format","json");
        aParams.put("appid","379020");
        aParams.put("bk_key","银魂");
        aParams.put("bk_length","600");
          HttpUtils.post().setUrl("http://baike.baidu.com/api/openapi/BaikeLemmaCardApi").params(aParams).execute(new CallBack() {
              @Override
              public void onSuccess(Object obj, long taskId) {

              }

              @Override
              public void onFailure(Object obj, long taskId) {

              }
          }).build(DataState.NET_FIRST);
    }

    public void  getData() {
        Map<String, String> aParams=new HashMap<>();
        aParams.put("scope","103");
        aParams.put("format","json");
        aParams.put("appid","379020");
        aParams.put("bk_key","银魂");
        aParams.put("bk_length","600");
        HttpUtils
                .get()
                .setUrl("http://baike.baidu.com/api/openapi/BaikeLemmaCardApi")
                .params(aParams)
                .taskId(0xf001)
                .execute(new CallBack() {
                    @Override
                    public void onSuccess(Object obj, long taskId) {
                        mText.setText(obj.toString());
                    }

                    @Override
                    public void onFailure(Object obj, long taskId) {

                    }
                }) .build(DataState.NO_CACHE);


    }
}
