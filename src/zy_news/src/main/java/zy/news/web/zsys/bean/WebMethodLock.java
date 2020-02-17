package zy.news.web.zsys.bean;

import java.lang.annotation.*;

/**
 * 本地锁注解
 *
 * @author fanpei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WebMethodLock {
    /**
     * 方法上某个值为key，为空时key有效
     *
     * @return
     */
    int argIndex() default -1;

    /**
     * @return
     */
    String key() default "";

    /**
     * 超时时间 单位秒，由于用的gua，暂时未用到，集成redis要用到
     *
     * @return
     */
    int expire() default 5;

    /**
     * 提示信息
     *
     * @return
     */
    String tips() default "";
}
