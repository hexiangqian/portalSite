package zy.news.web.zsys.bean;

import java.lang.annotation.*;

/**
 * 需要具有init()静态方法
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StaticClassInit {
}
