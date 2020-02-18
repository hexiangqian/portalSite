package zy.news.web.zsys.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.Expose;

/**
 * 自定义序列化策略--前端
 *
 * @author fanpei
 */
public class MyExclusionStrategyDer implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        MyExpose expose = f.getAnnotation(MyExpose.class);
        if (expose != null && !expose.deserialize()) {
            return true;
        } else {
            Expose exposeSys = f.getAnnotation(Expose.class);
            if (exposeSys != null && !exposeSys.deserialize()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }

}
