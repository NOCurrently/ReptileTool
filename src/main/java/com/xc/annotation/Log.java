/**
 * 
 */
package com.xc.annotation;

import java.lang.annotation.*;

/**
 *
 * @author 肖超
 * @date: 2019年7月26日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
	/**
	 * 操作类型
	 */
	String type() default "";

}
