package annotation;

import authority.DefaultAuthorityVerify;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    /**权限标识*/
    String [] value();

    /**具体执行校验类*/
    Class verify() default DefaultAuthorityVerify.class;
}
