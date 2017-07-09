package com.lee.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/7.
 */
public abstract class XMLBase {
    protected String name;
    protected List<XMLAttribute> XMLAttributes;

    public XMLBase(String name) {
        this.name = name;
        XMLAttributes = new ArrayList<>();
    }

    public boolean addAttribute(XMLAttribute XMLAttribute){
        if (XMLAttribute != null && !XMLAttributes.contains(XMLAttribute)){
            XMLAttributes.add(XMLAttribute);
            return true;
        }
        return false;
    }

    public boolean removeAttribute(XMLAttribute XMLAttribute){
        if (XMLAttribute != null && XMLAttributes.contains(XMLAttribute)){
            XMLAttributes.add(XMLAttribute);
            return true;
        }
        return false;
    }

    public abstract boolean addKids(XMLBase base);
    public abstract boolean removeKids(XMLBase base);
    public abstract void showKids();

    //通过 反射将读取出的xmlbase 实体类转换为 com.lee.xmlbean 优雅 哈哈哈
    @SuppressWarnings("Duplicates")
    public abstract Object transform();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XMLAttribute> getXMLAttributes() {
        return XMLAttributes;
    }

    public void setXMLAttributes(List<XMLAttribute> XMLAttributes) {
        this.XMLAttributes = XMLAttributes;
    }

    protected void valueFormat(String type,Object o,XMLAttribute XMLAttribute,Method method) throws InvocationTargetException, IllegalAccessException {
        if (type.contains(".String")){
            method.invoke(o,XMLAttribute.getValues());
        }else if (type.contains(".Integer")){
            Integer values;
            try {
                values = Integer.valueOf(XMLAttribute.getValues());
            }catch (NumberFormatException e){
                e.printStackTrace();
                values = 0;
            }
            method.invoke(o,values);
        }else if (type.contains(".Float")){
            Float values;
            try {
                values = Float.valueOf(XMLAttribute.getValues());
            }catch (NumberFormatException e){
                values = 0.0f;
                e.printStackTrace();
            }
            method.invoke(o,values);
        }else if(type.contains(".Boolean")){
            Boolean values;
            values = Boolean.valueOf(XMLAttribute.getValues());
            method.invoke(o,values);
        }
    }
}
