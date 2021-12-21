package my.qunz.myDemo.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dynamic {

    /**
     * 项目编号
     */
    String projectCode() default "";

}
