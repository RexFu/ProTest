package com.rex.protest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rex.td_http.base.Td;
import com.rex.td_http.listener.Callback;
import com.rex.td_http.proxy.HttpUtils;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGet;
    private Button mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Td.init(this);
        mGet=(Button) findViewById(R.id.btn_get);
        mPost=(Button) findViewById(R.id.btn_post);

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
    }

    public void  getData() {
               HttpUtils
                .get()
                .setUrl("http://apis.juhe.cn/goodbook/catalog")
                .taskId(0xf001).setParms("","").setParms("","")
                .build()
                .execute(new Callback() {
            @Override
            public void onSuccess(Object obj, int taskId) {

            }

            @Override
            public void onFailure(Object obj, int taskId) {

            }
        });
    }
}
