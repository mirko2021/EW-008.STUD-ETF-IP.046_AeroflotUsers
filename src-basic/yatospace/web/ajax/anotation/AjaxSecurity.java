package yatospace.web.ajax.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ознака да је потребно примјернити сигурносне мјере и 
 * провјере при приступу операциј преко АЈАКСа, односно 
 * директно из веб окружења клијената. 
 * @author MV
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AjaxSecurity {
}
