package zy.news.web.zsys.gson;

import java.lang.annotation.*;

/**
 * 自定义忽略字段 springboot框架序列化策略---前端
 * 
 * @author fanpei
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExpose {
	/**
	 * If {@code true}, the field marked with this annotation is written out in the
	 * JSON while serializing. If {@code false}, the field marked with this
	 * annotation is skipped from the serialized output. Defaults to {@code true}.
	 * 
	 * @since 1.4
	 */
	 boolean serialize() default true;

	/**
	 * If {@code true}, the field marked with this annotation is deserialized from
	 * the JSON. If {@code false}, the field marked with this annotation is skipped
	 * during deserialization. Defaults to {@code true}.
	 * 
	 * @since 1.4
	 */
	 boolean deserialize() default true;
}
