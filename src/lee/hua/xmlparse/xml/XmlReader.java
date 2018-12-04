package lee.hua.xmlparse.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/7.
 */
public class XmlReader {
    public static void XMLparse(Element rootElement,XMLBase root){
        XMLBase cur;
        if (rootElement.elements().size()>0){
            cur = new XMLHasKids(rootElement.getName());
            List<Attribute> attributes = rootElement.attributes();
            List<XMLAttribute> xmlAttributes = new ArrayList<>();
            for (Attribute attribute : attributes) {
                xmlAttributes.add(new XMLAttribute(attribute.getName(),attribute.getValue()));
            }
            cur.setXMLAttributes(xmlAttributes);
            root.addKids(cur);
            List<Element> childelement = rootElement.elements();
            for (Element element : childelement) {
                XMLparse(element,cur);
            }
        }else {
            cur = new XMLNoChilds(rootElement.getName());
            List<Attribute> attributes = rootElement.attributes();
            List<XMLAttribute> xmlAttributes = new ArrayList<>();
            for (Attribute attribute : attributes) {
                xmlAttributes.add(new XMLAttribute(attribute.getName(),attribute.getValue()));
            }
            cur.setXMLAttributes(xmlAttributes);
            root.addKids(cur);
        }
    }
}
