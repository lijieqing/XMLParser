package lee.hua.xmlparse.xmlbean;


import lee.hua.xmlparse.annotation.XmlAttribute;
import lee.hua.xmlparse.annotation.XmlBean;
import lee.hua.xmlparse.annotation.XmlSingleNode;

/**
 * Created by lijie on 2017/7/8.
 */
@XmlBean(name = "Knowledge")
public class Book {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer page;
    @XmlAttribute
    private Float price;
    @XmlSingleNode(name = "Publisher",nodeType = Publisher.class)
    private Publisher publishier;

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
