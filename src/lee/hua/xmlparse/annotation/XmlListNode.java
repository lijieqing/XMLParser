package lee.hua.xmlparse.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记List<nodeType>集合，标记后会生成对应集合数量的nodeType类型的XML标签
 * @author lijie
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlListNode {
    String name();

    Class<?> nodeType();
}
