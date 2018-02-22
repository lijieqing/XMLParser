package com.lee.xmlbean;


import com.lee.annotation.XmlAttribute;

/**
 * 作者
 *
 * @author lijie
 * @create 2018-02-22 20:31
 **/
public class Author {
    @XmlAttribute
    public String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
