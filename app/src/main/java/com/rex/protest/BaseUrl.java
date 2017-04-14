package com.rex.protest;

/**
 * Created by xianshang.liu on 2017/1/13.
 * api
 */

public class BaseUrl {
    //gq4.0host
    public static String HOST_URL = "http://application.gq.com.cn/mobileadmin/gq24/api40/";
    //老接口host
    public static String SET_URL = "http://application.gq.com.cn/mobileadmin/";
    //通用host
    public static String GENERAL_URL = "http://application.gq.com.cn/mobileadmin/general/api/";
    //评论host
    public static String COMMT_URL = "http://comment.gq.com.cn/ajaxcomment.php";

    //设置为调试模式
    public static void setDebugMode() {
        //gq4.0host
        HOST_URL = "http://application.test.gq.com.cn/mobileadmin/gq24/api40/";
        //老接口host
        SET_URL = "http://application.gq.com.cn/mobileadmin/";
        //通用host
        GENERAL_URL = "http://gq24v4.test.gq.com.cn/general/api/";
        //评论host
        COMMT_URL = "http://comment.test.vogue.com.cn/ajaxcomment.php";
    }
}
