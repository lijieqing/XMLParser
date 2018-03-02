package com.lee.xmlbean;

import com.lee.annotation.XmlAttribute;
import com.lee.annotation.XmlBean;

/**
 * Created by lijie on 2017/7/8.
 */
@XmlBean
public class Book {
    @XmlAttribute
    public String name;
    @XmlAttribute
    public Integer page;
    @XmlAttribute
    public Float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", page=" + page +
                ", price=" + price +
                '}';
    }
}
