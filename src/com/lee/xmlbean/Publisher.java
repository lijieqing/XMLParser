package com.lee.xmlbean;

import com.lee.annotation.XmlAttribute;
import com.lee.annotation.XmlBean;

/**
 * 出版社
 *
 * @author lijie
 * @create 2018-03-03 08:15
 **/
@XmlBean(name = "Publisher")
public class Publisher {
    @XmlAttribute(name = "PUB")
    public String pub;

    public Publisher() {
    }

    public Publisher(String pub) {
        this.pub = pub;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "pub='" + pub + '\'' +
                '}';
    }
}
