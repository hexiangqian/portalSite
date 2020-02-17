package zy.news.web.zsys.bean;


import java.lang.annotation.*;

/**
 * 执行对象+权限操作类型 二者且的关系
 * <p>
 * 但Roles和Types都为空则不检查权限 默认具有
 * fanpei 2019.12.09
 *
 * @author fanpei
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcutePermission {
    /**
     * 角色----具有此中一个角色即可
     *
     * @return
     */
    String[] Roles() default {};

    /**
     * 操作类型-----具有此中所有操作类型即可
     *
     * @return
     */
    PermissionType[] Types() default {};
}
