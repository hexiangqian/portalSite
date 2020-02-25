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
     * 用户类型，必须登录还是游客权限
     *
     * @return
     */
    ExcuteUserType userType() default ExcuteUserType.登录用户;
}
