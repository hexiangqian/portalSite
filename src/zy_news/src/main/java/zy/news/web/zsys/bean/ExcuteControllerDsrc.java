package zy.news.web.zsys.bean;

import java.lang.annotation.*;

/**
 * 执行对象描述
 * 
 * @author fanpei
 */
@Target({ ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcuteControllerDsrc {
	/**
	 * 模块名称
	 * 
	 * @return
	 */
	String value() default "";
}
