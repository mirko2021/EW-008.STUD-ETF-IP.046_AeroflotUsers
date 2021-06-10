package yatospace.web.ajax.anotation;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Методе које имају приступ за ајакст. 
 * Као параметре требале би имати ЈСОН 
 * контекст, а овај би требао имати 
 * минимално два дијела од ЈСОН. 
 * @author MV
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AjaxAccessable {
	public String value() default ""; 
}
