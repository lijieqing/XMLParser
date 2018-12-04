package lee.hua.xmlparse.xml;

/**
 * Created by lijie on 2017/6/7.
 */
public class XMLAttribute {
    private String name;
    private String values;

    public XMLAttribute(String name, String values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "XMLAttribute{" +
                "name='" + name + '\'' +
                ", values='" + values + '\'' +
                '}';
    }
}
