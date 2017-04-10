package com.rex.td_http.proxy;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rex on 2016/6/17.
 * 解析代理
 */
public class ParserProxy {

    private static volatile ParserProxy instance = new ParserProxy();

    private ParserProxy() {
        mGson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static synchronized ParserProxy getInstance() {
        return instance;
    }

    /**
     * 私有静态对象gson
     */
    private static Gson mGson;

    /**
     * turn obj to json , include null;
     *
     * @param object
     * @return
     */
    public String toJson(Object object) {
        return mGson.toJson(object);
    }

    /**
     * convert json String to Object.
     *
     * @param jsonString json string
     * @param clazz      Object class
     * @param <T>        Object Class Type
     * @return Object
     */
    public <T> T parse(String jsonString, Class<T> clazz) {
        try {
            return mGson.fromJson(jsonString, clazz);
        } catch (Throwable var3) {
            var3.printStackTrace();
            return (T) new Object();
        }
    }

    /**
     * convert json string to the Object of the specified Type.
     *
     * @param json    json string
     * @param typeOfT Type
     * @param <T>     Type
     * @return Object of T
     */
    public <T> T parse(String json, Type typeOfT) {
        try {
            return mGson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    //单为类型分不清楚的php设计。。。。。。
    public <T> T parseForStupidphp(String json, Type typeOfT) {
        try {
            Gson tempGson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).setPrettyPrinting().create();
            return tempGson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    //过滤字段
    public <T> T parseWithFilter(String json, Type typeOfT, final String... params) {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    //过滤掉字段名包含"id","address"的字段
                    for (int i = 0; i < params.length; i++) {
                        if (f.getName().contains(params[i])) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    //过滤掉 类名包含 Bean的类
                    return false;
//                    return clazz.getName().contains("Bean");
                }
            });
            Gson tempGson = gsonBuilder.create();
            return tempGson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    //当类型为数组时，强制将{}解析为数组
    public class ArrayAdapter<T> extends TypeAdapter<List<T>> {
        private Class<T> adapterclass;

        public ArrayAdapter(Class<T> adapterclass) {
            this.adapterclass = adapterclass;
        }

        public List<T> read(JsonReader reader) throws IOException {

            List<T> list = new ArrayList<T>();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new ArrayAdapterFactory())
                    .create();

            if (reader.peek() == JsonToken.BEGIN_OBJECT) {
                T inning = gson.fromJson(reader, adapterclass);
                list.add(inning);

            } else if (reader.peek() == JsonToken.BEGIN_ARRAY) {

                reader.beginArray();
                while (reader.hasNext()) {
                    T inning = gson.fromJson(reader, adapterclass);
                    list.add(inning);
                }
                reader.endArray();

            }
            return list;
        }

        public void write(JsonWriter writer, List<T> value) throws IOException {

        }

    }

    public class ArrayAdapterFactory implements TypeAdapterFactory {

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {

            TypeAdapter<T> typeAdapter = null;

            try {
                if (type.getRawType() == List.class)
                    typeAdapter = new ArrayAdapter(
                            (Class) ((ParameterizedType) type.getType())
                                    .getActualTypeArguments()[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return typeAdapter;


        }

    }

}
