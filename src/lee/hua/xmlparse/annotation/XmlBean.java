package lee.hua.xmlparse.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记类信息，表示该类为XML标签类，使用此注解标记的类，
 * 若无特殊标记（特指Ignore）则所有的字段会生成对应的XML信息
 * @author lijie
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlBean {
    String name() default "";
}
