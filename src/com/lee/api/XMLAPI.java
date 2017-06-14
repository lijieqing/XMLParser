package com.lee.api;

import com.lee.xml.*;
import com.lee.xmlbean.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/7.
 */
public class XMLAPI {
    private static Document document = null;

    //返回 xmlbean 包下的根目录

    public static Object readXML(InputStream inputStream){
        Element rootElement = null;
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //先 将root 节点解析出来
        if (document != null)rootElement = document.getRootElement();
        XMLBase root = new XMLHasKids(rootElement.getName());
        List<Attribute> attributes = rootElement.attributes();
        List<XMLAttribute> xmlAttributes = new ArrayList<>();
        for (Attribute attribute : attributes) {
            xmlAttributes.add(new XMLAttribute(attribute.getName(),attribute.getValue()));
        }
        root.setXMLAttributes(xmlAttributes);

        //利用递归 将子节点逐一解析 放入xmlbase 中
        List<Element> childelement = rootElement.elements();
        for (Element element : childelement) {
            XmlReader.XMLparse(element,root);
        }

        return root.transform();
    }

    public static void SHOWXMLINFO(Object o){
        Device device = (Device) o;
        RealTimeSet rels = device.getRealTimeSet();
        List<RealTimeParam> reals = rels.getRealTimeParams();
        for (RealTimeParam real : reals) {
            System.out.println(device.getDevBornDate()+"real RealTimeParam "+real.getName());
        }

        DataSet dataset = device.getDataSet();
        List<DSItem> dsitems = dataset.getDsItems();
        for (DSItem dsitem : dsitems) {
            System.out.println(device.getDevBornDate()+"dsitem DSItem "+dsitem.getName());
        }

        J1939 j1939 = device.getJ1939();
        List<PG> pgs = j1939.getPgs();
        for (PG pg : pgs) {
            System.out.println(device.getDevBornDate()+"pg PG "+pg.getPGN());
            List<SP> sps = pg.getSps();
            for (SP sp : sps) {
                System.out.println(device.getDevBornDate()+"--- SP sp "+sp.getSPN());
            }
        }

        QCSet qcset = device.getQcSet();
        List<QCItem> qcitems = qcset.getQcItems();
        for (QCItem qcitem : qcitems) {
            System.out.println(device.getDevBornDate()+"--- QCItem qcitem "+qcitem.getName());
        }

        System.out.println(device);
    }


    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("D:/temp.xml");
            Object o = readXML(inputStream);
            SHOWXMLINFO(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
