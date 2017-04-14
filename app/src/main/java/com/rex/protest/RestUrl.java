package com.rex.protest;


import static com.rex.protest.BaseUrl.GENERAL_URL;
import static com.rex.protest.BaseUrl.HOST_URL;
import static com.rex.protest.BaseUrl.SET_URL;

/**
 * Created by xianshang.liu on 2016/10/14.
 * 全局数据接口地址的集合，保持final，避免程序中失误改动，该类首次载入时即完成所有数值初始化
 */

public class  RestUrl {

    /**
     * 以下为业务path
     */

    //社区页面中，精选和最新页面的列表
    public static final String Community_URL = HOST_URL + "gethgrulist";
    //发现页面,热门搜索标签
    public static final String HOT_SEARCH_URL = HOST_URL + "gethotsearch";
    //发现页面,热门标签
    public static final String HOT_TAG = HOST_URL + "gethottags";
    //发现页面,穿衣达人
    public static final String DRESS_EXPERT = HOST_URL + "getdressmasterlist";
    //发现页面,热门装扮
    public static final String HOT_DRESS = HOST_URL + "gethothgrulist";
    //发现页面,四个栏目
    public static final String HOT_COLUMN = HOST_URL + "getalbumlist";
    //主页面，文章接口
    public static final String HOME_ARTICLE = HOST_URL + "getindexdata";
    //文章底层页，详细数据接口
    public static final String DETAIL_ARTICLE = HOST_URL + "article/html";
    //设置页面，精彩推荐
    public static final String SETTING_RECOMMENT = SET_URL + "general/api/recommend";
    //设置页面，精彩推荐图片
    public static final String SETTING_RECOMMENT_BANNER = SET_URL + "gq24/api/appbanner";
    //设置页面，意见反馈
    public static final String SETTING_FEEDBACK = GENERAL_URL + "feedback";
    //个人资料，获取真实姓名
    public static final String PERSON_REAL_NAME = HOST_URL + "getrealname";
    //个人资料，更改真实姓名
    public static final String CHANG_REAL_NAME = HOST_URL + "addrealname";
    //个人资料，获取地址
    public static final String PERSON_ADDRESS = GENERAL_URL + "getaddress";
    //个人资料，修改地址
    public static final String CHNAG_ADDRESS = GENERAL_URL + "updateaddress";
    //用户页面，收藏和评论
    public static final String USER_TOP = HOST_URL + "getusertopinfo";
    //用户页面，我的上传列表
    public static final String UPLOAD_DRESS_LIST = HOST_URL + "getmyhgrulist";
    //装扮页面，获取装扮的标签
    public static final String DRESS_TAG = HOST_URL + "getuploadhottags";
    //装扮页面，上传装扮，图片
    public static final String DRESS_TAG_IMAG = "http://imgsys.self.com.cn/interface";
    //装扮页面，上传装扮，标签
    public static final String DRESS_LABLE_COODE = HOST_URL + "hgru/upload";
    //标签装扮列表
    public static final String TAG_LIST = HOST_URL + "gethowgrubytagid";
    //标签文章列表
    public static final String ARTICLE_TAG_LIST = HOST_URL + "getarticlelistbytag";
    //个人装扮列表
    public static final String PERSON_DRESS_LIST = HOST_URL + "getuserhgrulist";
    //个人装扮列表,删除装扮
    public static final String PERSON_DRESS_DELET = HOST_URL + "hgru/delete";
    //个人收藏列表
    public static final String PERSON_COLLECTOION = HOST_URL + "mycollectlist";
    //个人体验中心页面
    public static final String EXPERIENCE = "http://brand.m.gq.com.cn/try/";
    //个人收藏列表,删除收藏
    public static final String PERSON_COLLECTOION_DELET = GENERAL_URL + "cancelcollectarticle";
    //获得用户某篇文章的收藏状态
    public static final String ARTICLE_COLLECT_STATE = GENERAL_URL + "checkcollectforlogin";
    //添加收藏
    public static final String COLLECT_ADD = GENERAL_URL + "collectarticle";
    //点赞
    public static final String SET_PRAISE = GENERAL_URL + "setpraise";
    //取消点赞
    public static final String CANCEL_PRAISE = GENERAL_URL + "cancelpraise";
    //获得某个装扮详细
    public static final String GET_HGRU_DETAIL = HOST_URL + "hgru/detail";
    //根据某个tagId获取装扮列表，编辑点评需要
    public static final String GET_EDITOR = HOST_URL + "gethowgrubytagid";
    //根据某个栏目id获取栏目列表
    public static final String COLUMN_LIST = HOST_URL + "getariticlebychannelid";
    //获取栏目id和名称映射表
    public static final String COLUMN_MAP = HOST_URL + "channels";
    //评论
    public static final String COMMT_URL = BaseUrl.COMMT_URL;




}
