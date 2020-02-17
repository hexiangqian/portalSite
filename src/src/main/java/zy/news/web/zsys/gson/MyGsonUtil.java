package zy.news.web.zsys.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maoko.common.gson.JSONUtil;


/**
 * 前端界面JSON助手
 *
 * @author fanpei
 */
public class MyGsonUtil extends JSONUtil {
    public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    public static Gson normalGson;

    static {
        gson = null;
        gson = getGson();
        normalGson = getNormalGson();
        //prParser = new JsonParser();
    }

    /**
     * 初始化 保证先进MyGsonUtil static 函数
     */
    public static void init() {
    }

    public static Gson getGson() {
        if (null == gson) {
            gson = new GsonBuilder().setDateFormat(DATEFORMAT)//
                    .serializeNulls()//
                   .addDeserializationExclusionStrategy(new MyExclusionStrategyDer())//
                    .addSerializationExclusionStrategy(new MyExclusionStrategySer())//
                    //.registerTypeAdapterFactory(new MyTypeAdapterFactory())
                    //  .setFieldNamingStrategy(new FeildNameStrategy())
                    .create();
        }
        return gson;
    }

    @Deprecated
    public static Gson getNormalGson() {
        return new GsonBuilder().setDateFormat(DATEFORMAT)//
                .serializeNulls()//
                .addDeserializationExclusionStrategy(new MyExclusionStrategyDer())//
                .addSerializationExclusionStrategy(new MyExclusionStrategySer())//
                //.registerTypeAdapter(Date.class, new GsonDateTypeAdapter())
                .create();
    }

}
