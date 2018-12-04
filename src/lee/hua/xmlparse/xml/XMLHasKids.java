package lee.hua.xmlparse.xml;

import lee.hua.xmlparse.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/7.
 */
public class XMLHasKids extends XMLBase {
    private List<XMLBase> childs;
    private List<Object> kids;

    public List<XMLBase> getChilds() {
        return childs;
    }

    public void setChilds(List<XMLBase> childs) {
        this.childs = childs;
    }

    public XMLHasKids(String name) {
        super(name);
        childs = new ArrayList<>();
        kids = new ArrayList<>();
    }

    @Override
    public boolean addKids(XMLBase base) {
        if (base != null && !childs.contains(base)) {
            childs.add(base);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeKids(XMLBase base) {
        if (base != null && childs.contains(base)) {
            childs.remove(base);
            return true;
        }
        return false;
    }

    @Override
    public void showKids() {
        System.out.println("name:" + name + "XMLAttributes:--start");
        for (XMLAttribute XMLAttribute : XMLAttributes) {
            System.out.print(XMLAttribute);
        }
        System.out.println("name:" + name + "XMLAttributes:--end");
        System.out.println("name:" + name + " show child:--start");
        for (XMLBase child : childs) {
            child.showKids();
        }
        System.out.println("name:" + name + " show child:--end");
    }

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
            Field[] filds = clazz.getDeclaredFields();
            XmlBean xmlBean = clazz.getAnnotation(XmlBean.class);

            for (Field field : filds) {
                //当注解不是ignore时
                Ignore ignore = field.getAnnotation(Ignore.class);
                if ((xmlBean != null && ignore == null) || (xmlBean == null && ignore == null)) {
                    //存在 XmlAttribute注解时进行解析
                    XmlAttribute attr = field.getAnnotation(XmlAttribute.class);
                    //默认属性名为字段名
                    String attrName = field.getName().toLowerCase();
                    String nodeName = field.getName().toLowerCase();

                    //存在XmlSingleNode注解时进行解析
                    XmlSingleNode singleNode = field.getAnnotation(XmlSingleNode.class);
                    //存在XmlListNode注解时进行解析
                    XmlListNode listNode = field.getAnnotation(XmlListNode.class);
                    //设置跳过 Java 语言检查
                    field.setAccessible(true);
                    if (attr != null) {
                        //当注解对象中的属性名不是默认值时，为当前属性名赋值
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name().toLowerCase();
                        }
                        //遍历属性集合
                        for (XMLAttribute xmlAttr : XMLAttributes) {
                            String name = xmlAttr.getName().toLowerCase();
                            System.out.println(name + xmlAttr.getValues());
                            if (attrName.equals(name)) {
                                String type = field.getGenericType().toString();
                                //寻找属性对应的set方法
                                valueFormat(type, o, xmlAttr, field);
                                break;
                            }
                        }
                    } else if (singleNode != null) {
                        if (!"".equals(singleNode.name().trim())) {
                            nodeName = singleNode.name().trim();
                        }else {
                            nodeName = field.getName();
                        }
                        for (XMLBase child : childs) {
                            String childName = child.name;
                            if (childName.equals(nodeName)) {
                                field.set(o, child.transform());
                                break;
                            }
                        }
                    } else if (listNode != null) {
                        nodeName = listNode.name().trim();
                        for (XMLBase child : childs) {
                            String chlName = child.name;

                            if (chlName.equals(nodeName)) {
                                kids.add(child.transform());
                            }
                        }
                        if (kids.size() > 0) {
                            List<Object> value = new ArrayList<>(kids);
                            field.set(o, value);
                            kids.clear();
                        }
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("package name is not write");
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
