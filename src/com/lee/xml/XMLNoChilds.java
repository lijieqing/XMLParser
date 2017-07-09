package com.lee.xml;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        System.out.println("name:"+name+"XMLAttributes:--start");
        for (XMLAttribute XMLAttribute : XMLAttributes) {
            System.out.print(XMLAttribute);
        }
        System.out.println("name:"+name+"XMLAttributes:--end");
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Object transform() {
        String className = Globals.CLASSNAME+this.name;
        Object o = null;
        try {
            Class clazz = Class.forName(className);
            o = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            Field[] filds = clazz.getDeclaredFields();
            //属性操作
            for (XMLAttribute XMLAttribute : XMLAttributes) {
                String name = XMLAttribute.getName().toLowerCase();
                String type = "";
                for (Field fild : filds) {
                    if (fild.getName().toLowerCase().equals(name)){
                        type = fild.getGenericType().toString();
                    }
                }
                for (Method method : methods) {
                    String mName = method.getName().toLowerCase();
                    if (("set"+name).equals(mName)) {
                        valueFormat(type,o,XMLAttribute,method);
                    }
                }
            }

        } catch (ClassNotFoundException e) {
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
