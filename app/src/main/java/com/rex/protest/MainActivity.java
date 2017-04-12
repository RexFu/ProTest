package com.rex.protest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rex.td_http.MvcPointer;
import com.rex.td_http.RetrofitProxy;
import com.rex.td_http.base.DataState;
import com.rex.td_http.base.GetAction;
import com.rex.td_http.base.HttpUtils;
import com.rex.td_http.base.TActionListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGet;
    private Button mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MvcPointer.init(this, true, RetrofitProxy.getInstance(this));
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
        HttpUtils
                .get()
                .setUrl("http://apis.juhe.cn/goodbook/catalog")
                .params(aParams)
                .taskId(0xf001)
                .build(DataState.CACHE_FIRST)
                .execute(new TActionListener() {
            @Override
            public void onSuccess(Object obj, long taskId) {

            }

            @Override
            public void onFailure(Object obj, long taskId) {

            }
        });

    }

    public void  getData() {
//               HttpUtils
//                .GetAction()
//                .setUrl("http://apis.juhe.cn/goodbook/catalog")
//                .taskId(0xf001).setParms("","").setParms("","")
//                .build()
//                .execute(new Callback() {
//            @Override
//            public void onSuccess(Object obj, int taskId) {
//
//            }
//
//            @Override
//            public void onFailure(Object obj, int taskId) {
//
//            }
//        });
    }
}
