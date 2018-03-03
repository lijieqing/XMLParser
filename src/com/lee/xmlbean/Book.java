package com.lee.xmlbean;

import com.lee.annotation.XmlAttribute;
import com.lee.annotation.XmlBean;
import com.lee.annotation.XmlSingleNode;

/**
 * Created by lijie on 2017/7/8.
 */
@XmlBean(name = "Knowledge")
public class Book {
    @XmlAttribute
    public String name;
    @XmlAttribute
    public Integer page;
    @XmlAttribute
    public Float price;
    @XmlSingleNode(name = "Publisher",nodeType = Publisher.class)
    public Publisher publishier;

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

    public Publisher getPublishier() {
        return publishier;
    }

    public void setPublishier(Publisher publishier) {
        this.publishier = publishier;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", page=" + page +
                ", price=" + price +
                ", publishier=" + publishier +
                '}';
    }
}
