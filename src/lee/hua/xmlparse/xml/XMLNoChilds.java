package lee.hua.xmlparse.xml;

import lee.hua.xmlparse.annotation.Ignore;
import lee.hua.xmlparse.annotation.XmlAttribute;

import java.lang.reflect.Field;

/**
 * Created by lijie on 2017/6/7.
 */
public class XMLNoChilds extends XMLBase {
    public XMLNoChilds(String name) {
        super(name);
    }

    @Override
    public boolean addKids(XMLBase base) {
        return false;
    }

    @Override
    public boolean removeKids(XMLBase base) {
        return false;
    }

    @Override
    public void showKids() {
        System.out.println("name:" + name + "XMLAttributes:--start");
        for (XMLAttribute XMLAttribute : XMLAttributes) {
            System.out.print(XMLAttribute);
        }
        System.out.println("name:" + name + "XMLAttributes:--end");
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Object transform() {
        String className = Globals.getBeanClass(this.name);
        if ("".equals(className)) {
            return null;
        }
        Object o = null;
        try {
            Class<?> clazz = Class.forName(className);
            o = clazz.newInstance();
            Field[] fileds = clazz.getDeclaredFields();

            for (Field field : fileds) {
                //当注解不是ignore时
                Ignore ignore = field.getAnnotation(Ignore.class);
                if (ignore == null) {
                    //设置跳过 Java 语言检查
                    field.setAccessible(true);
                    //存在 XmlAttribute注解时进行解析
                    XmlAttribute attr = field.getAnnotation(XmlAttribute.class);
                    //默认属性名为字段名
                    String attrName = field.getName().toLowerCase();
                    if (attr != null) {
                        //当注解对象中的属性名不是默认值时，为当前属性名赋值
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name().trim().toLowerCase();
                        }
                        //遍历属性集合
                        for (XMLAttribute xmlAttr : XMLAttributes) {
                            String name = xmlAttr.getName().toLowerCase();
                            if (attrName.equals(name)) {
                                String type = field.getGenericType().toString();
                                //寻找属性对应的set方法
                                valueFormat(type, o, xmlAttr, field);
                                break;
                            }
                        }
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return o;
    }
}
