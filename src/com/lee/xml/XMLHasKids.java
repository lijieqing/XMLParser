package com.lee.xml;

import com.lee.annotation.*;

import java.lang.annotation.Annotation;
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
        String className = Globals.CLASSNAME + this.name;
        Object o = null;
        try {
            Class clazz = Class.forName(className);
            o = clazz.newInstance();
            Field[] filds = clazz.getDeclaredFields();
            Annotation xmlBean = clazz.getAnnotation(XmlBean.class);

            for (Field fild : filds) {
                //当注解不是ignore时
                Ignore ignore = fild.getAnnotation(Ignore.class);
                if ((xmlBean!=null&&ignore==null) || (xmlBean==null&&ignore==null)) {
                    //存在 XmlAttribute注解时进行解析
                    XmlAttribute attr = fild.getAnnotation(XmlAttribute.class);
                    //默认属性名为字段名
                    String attrName = fild.getName().toLowerCase();

                    //存在XmlSingleNode注解时进行解析
                    XmlSingleNode singleNode = fild.getAnnotation(XmlSingleNode.class);
                    String singleNodeName = fild.getName().toLowerCase();

                    //存在XmlListNode注解时进行解析
                    XmlListNode listNode = fild.getAnnotation(XmlListNode.class);

                    if (attr != null) {
                        //当注解对象中的属性名不是默认值时，为当前属性名赋值
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name();
                        }
                        //遍历属性集合
                        for (XMLAttribute xmlAttr : XMLAttributes) {
                            String name = xmlAttr.getName().toLowerCase();
                            System.out.println(name + xmlAttr.getValues());
                            if (attrName.equals(name)) {
                                String type = fild.getGenericType().toString();
                                //寻找属性对应的set方法
                                valueFormat(type, o, xmlAttr, fild);
                                break;
                            }
                        }
                    } else if (singleNode != null) {
                        if (!"".equals(singleNode.name().trim())) {
                            singleNodeName = singleNode.name().toLowerCase();
                        }
                        for (XMLBase child : childs) {
                            String childName = child.name.toLowerCase();
                            if (childName.equals(singleNodeName)) {
                                String singleType = singleNode.nodeType().toString();
                                child.name = singleType.substring(singleType.lastIndexOf(".") + 1);
                                fild.set(o, child.transform());
                                break;
                            }
                        }
                    } else if (listNode != null) {
                        String name = listNode.name().trim().toLowerCase();
                        for (XMLBase child : childs) {
                            String chlName = child.name.toLowerCase();
                            if (chlName.equals(name)) {
                                String listType = listNode.nodeType().toString();
                                child.name = listType.substring(listType.lastIndexOf(".") + 1);
                                kids.add(child.transform());
                            }
                        }
                        if (kids.size() > 0) {
                            List<Object> value = new ArrayList<>(kids);
                            fild.set(o, value);
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
