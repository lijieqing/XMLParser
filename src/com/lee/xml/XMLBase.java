package com.lee.xml;

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
}
