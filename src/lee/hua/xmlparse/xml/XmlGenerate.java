package lee.hua.xmlparse.xml;

import lee.hua.xmlparse.annotation.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by lijie on 2017/7/6.
 */
@SuppressWarnings("Duplicates")
public class XmlGenerate {
    private XmlGenerate() {
    }

    private static void write(Element rootElement, Object object) throws IllegalAccessException {
        String name;
        Class<? extends Object> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        //获取类注解 XmlBean
        XmlBean xmlBean = clazz.getAnnotation(XmlBean.class);

        for (Field field : fields) {
            Ignore ignore = field.getAnnotation(Ignore.class);
            //当属性 不能忽略 或者 注解了XmlBean时
            if ((xmlBean != null && ignore == null) || (xmlBean == null && ignore == null)) {
                //设置跳过 Java 语言检查
                field.setAccessible(true);

                XmlAttribute attr = field.getAnnotation(XmlAttribute.class);

                String type = field.getGenericType().toString();
                if (type.contains(".String")) {
                    String attrName = field.getName();
                    if (attr != null) {
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name();
                        }
                    }
                    String value = (String) field.get(object);
                    rootElement.addAttribute(attrName, value);
                } else if (type.contains("int")) {
                    String attrName = field.getName();
                    if (attr != null) {
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name();
                        }
                    }
                    int value = field.getInt(object);
                    rootElement.addAttribute(attrName, value + "");
                } else if (type.contains(".Integer")) {
                    String attrName = field.getName();
                    if (attr != null) {
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name();
                        }
                    }
                    int value = (int) field.get(object);
                    rootElement.addAttribute(attrName, value + "");
                } else if (type.contains("float")) {
                    String attrName = field.getName();
                    if (attr != null) {
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name();
                        }
                    }
                    float value = field.getFloat(object);
                    rootElement.addAttribute(attrName, value + "");
                } else if (type.contains(".Float")) {
                    String attrName = field.getName();
                    if (attr != null) {
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name();
                        }
                    }
                    float value = (float) field.get(object);
                    rootElement.addAttribute(attrName, value + "");
                } else if (type.contains("boolean")) {
                    String attrName = field.getName();
                    if (attr != null) {
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name();
                        }
                    }
                    boolean value = field.getBoolean(object);
                    rootElement.addAttribute(attrName, value + "");
                } else if (type.contains(".Boolean")) {
                    String attrName = field.getName();
                    if (attr != null) {
                        if (!"".equals(attr.name().trim())) {
                            attrName = attr.name();
                        }
                    }
                    boolean value = (boolean) field.get(object);
                    rootElement.addAttribute(attrName, value + "");
                } else if (type.contains(".List")) {
                    name = field.getGenericType().toString();
                    name = name.substring(name.lastIndexOf(".") + 1, name.length() - 1);
                    XmlListNode listNode = field.getAnnotation(XmlListNode.class);
                    if (!"".equals(listNode.name().trim())) {
                        name = listNode.name().trim();
                    }
                    if(field.get(object) != null){
                        List list = (List) field.get(object);
                        for (Object o : list) {
                            Element element = rootElement.addElement(name);
                            write(element, o);
                        }
                    }
                } else {
                    name = field.getGenericType().toString();
                    name = name.substring(name.lastIndexOf(".") + 1);
                    XmlSingleNode singleNode = field.getAnnotation(XmlSingleNode.class);
                    if (!"".equals(singleNode.name().trim())) {
                        name = singleNode.name().trim();
                    }
                    Object value = field.get(object);
                    if (value != null) {
                        Element element = rootElement.addElement(name);
                        write(element, value);
                    }
                }
            }
        }
    }


    /**
     * Generate.
     *
     * @param object  数据源，待转化成xml的实体类
     * @param DesPath xml文件生成路径
     * @throws IllegalAccessException the illegal access exception
     * @throws IOException            the io exception
     */
    public static void generate(Object object, String DesPath) throws IllegalAccessException, IOException {
        String name;
        Class<?> clazz = object.getClass();
        //获取类注解 XmlBean
        XmlBean xmlBean = clazz.getAnnotation(XmlBean.class);
        if (xmlBean != null) {
            String rootname = clazz.getName();
            if (!"".equals(xmlBean.name().trim())) {
                rootname = xmlBean.name().trim();
            } else {
                rootname = rootname.substring(rootname.lastIndexOf(".") + 1);
            }

            Field[] fields = clazz.getDeclaredFields();
            Element root = DocumentHelper.createElement(rootname);
            // 创建文档并设置文档的根元素节点
            Document doucment = DocumentHelper.createDocument(root);

            for (Field field : fields) {
                Ignore ignore = field.getAnnotation(Ignore.class);
                //当属性 不能忽略 或者 注解了XmlBean时
                if (ignore == null) {
                    //设置跳过 Java 语言检查
                    field.setAccessible(true);

                    XmlAttribute attr = field.getAnnotation(XmlAttribute.class);

                    String type = field.getGenericType().toString();
                    if (type.contains(".String")) {
                        String attrName = field.getName();
                        if (attr != null) {
                            if (!"".equals(attr.name().trim())) {
                                attrName = attr.name();
                            }
                        }
                        String value = (String) field.get(object);
                        root.addAttribute(attrName, value);
                    } else if (type.contains("int")) {
                        String attrName = field.getName();
                        if (attr != null) {
                            if (!"".equals(attr.name().trim())) {
                                attrName = attr.name();
                            }
                        }
                        int value = field.getInt(object);
                        root.addAttribute(attrName, value + "");
                    } else if (type.contains(".Integer")) {
                        String attrName = field.getName();
                        if (attr != null) {
                            if (!"".equals(attr.name().trim())) {
                                attrName = attr.name();
                            }
                        }
                        int value = (int) field.get(object);
                        root.addAttribute(attrName, value + "");
                    } else if (type.contains("float")) {
                        String attrName = field.getName();
                        if (attr != null) {
                            if (!"".equals(attr.name().trim())) {
                                attrName = attr.name();
                            }
                        }
                        float value = field.getFloat(object);
                        root.addAttribute(attrName, value + "");
                    } else if (type.contains(".Float")) {
                        String attrName = field.getName();
                        if (attr != null) {
                            if (!"".equals(attr.name().trim())) {
                                attrName = attr.name();
                            }
                        }
                        float value = (float) field.get(object);
                        root.addAttribute(attrName, value + "");
                    } else if (type.contains("boolean")) {
                        String attrName = field.getName();
                        if (attr != null) {
                            if (!"".equals(attr.name().trim())) {
                                attrName = attr.name();
                            }
                        }
                        boolean value = field.getBoolean(object);
                        root.addAttribute(attrName, value + "");
                    } else if (type.contains(".Boolean")) {
                        String attrName = field.getName();
                        if (attr != null) {
                            if (!"".equals(attr.name().trim())) {
                                attrName = attr.name();
                            }
                        }
                        boolean value = (boolean) field.get(object);
                        root.addAttribute(attrName, value + "");
                    } else if (type.contains(".List")) {
                        name = field.getGenericType().toString();
                        name = name.substring(name.lastIndexOf(".") + 1, name.length() - 1);
                        XmlListNode listNode = field.getAnnotation(XmlListNode.class);
                        if (!"".equals(listNode.name().trim())) {
                            name = listNode.name().trim();
                        }
                        if(field.get(object) != null){
                            List list = (List) field.get(object);
                            for (Object o : list) {
                                Element element = root.addElement(name);
                                write(element, o);
                            }
                        }
                    } else {
                        name = field.getGenericType().toString();
                        name = name.substring(name.lastIndexOf(".") + 1);
                        XmlSingleNode singleNode = field.getAnnotation(XmlSingleNode.class);
                        if (!"".equals(singleNode.name().trim())) {
                            name = singleNode.name().trim();
                        }
                        Object value = field.get(object);
                        if (value != null) {
                            Element element = root.addElement(name);
                            write(element, value);
                        }
                    }
                }
                // 设置XML文档格式
                OutputFormat outputFormat = OutputFormat.createPrettyPrint();
                // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
                outputFormat.setEncoding("UTF-8");
                //outputFormat.setSuppressDeclaration(true); //是否生产xml头
                outputFormat.setIndent(true); //设置是否缩进
                outputFormat.setIndent("    "); //以四个空格方式实现缩进
                outputFormat.setNewlines(true); //设置是否换行

                //添加
                FileOutputStream file = new FileOutputStream(DesPath);
                XMLWriter xmlwriter2 = new XMLWriter(file, outputFormat);
                xmlwriter2.write(doucment);
                xmlwriter2.flush();
                xmlwriter2.close();
            }
        }
    }

}

