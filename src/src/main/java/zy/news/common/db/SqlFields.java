package zy.news.common.db;

import java.lang.annotation.*;

/**
 * Mysql数据库字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SqlFields {

    SqlField[] value();
}
