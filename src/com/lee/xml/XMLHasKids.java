package com.lee.xml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        if (base!=null && !childs.contains(base)){
            childs.add(base);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeKids(XMLBase base) {
        if (base!=null && childs.contains(base)){
            childs.remove(base);
            return true;
        }
        return false;
    }

    @Override
    public void showKids() {
        System.out.println("name:"+name+"XMLAttributes:--start");
        for (XMLAttribute XMLAttribute : XMLAttributes) {
            System.out.print(XMLAttribute);
        }
        System.out.println("name:"+name+"XMLAttributes:--end");
        System.out.println("name:"+name+" show child:--start");
        for (XMLBase child : childs) {
            child.showKids();
        }
        System.out.println("name:"+name+" show child:--end");
    }

    @Override
    public Object transform() {
        String className = Globals.CLASSNAME+this.name;
        Object o = null;
        try {
            Class clazz = Class.forName(className);
            o = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            Field[] filds = clazz.getDeclaredFields();

            //属性操作 把标签属性写入实例中
            for (XMLAttribute XMLAttribute : XMLAttributes) {
                String name = XMLAttribute.getName().toLowerCase();
                String type = "";
                for (Field fild : filds) {
                    if (fild.getName().toLowerCase().equals(name)){
                        type = fild.getGenericType().getTypeName();
                    }
                }
                for (Method method : methods) {
                    String mName = method.getName().toLowerCase();
                    if (("set"+name).equals(mName)){
                        valueFormat(type,o,XMLAttribute,method);
                        break;
                    }
                }
            }
            //非属性,非集合 设置 把当前对象中存在的其他对象的引用数据写入（not list）
            for (Method method : methods) {
                String mName = method.getName().toLowerCase();
                for (XMLBase child : childs) {
                    String chlName = child.name.toLowerCase();
                    if (("set"+chlName).equals(mName)){
                        //调用子节点的transform
                        Object obj = child.transform();
                        method.invoke(o,obj);
                        break;
                    }
                }

            }
            // 非属性 集合 设置 把当前对象中的集合类引用数据写入
            for (Method method : methods) {
                String mName = method.getName().toLowerCase();
                for (XMLBase child : childs) {
                    String chlName = child.name.toLowerCase();
                    if (("set"+chlName+"s").equals(mName)){
                        kids.add(child.transform());
                    }
                }
                if (kids.size()>0){
                    method.invoke(o,new Object[]{kids});
                    kids.clear();
                }
            }


        } catch (ClassNotFoundException e) {
            System.out.println("package name is not write");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return o;
    }
}
