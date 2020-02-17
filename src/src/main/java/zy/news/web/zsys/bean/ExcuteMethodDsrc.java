package zy.news.web.zsys.bean;

import java.lang.annotation.*;

/**
 * 方法描述
 *
 * @author fanpei
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcuteMethodDsrc {

    /**
     * 操作名称
     *
     * @return
     */
    String value() default "";
}
