package com.lee.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by lijie on 2017/7/6.
 */
@SuppressWarnings("Duplicates")
public class XmlGenerate {
    private XmlGenerate(){}
    private static void write(Element rootElement, Object object) throws InvocationTargetException, IllegalAccessException {

        Class<? extends Object> clazz = object.getClass();
        Field[] filds = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        for (Field fild : filds) {
            String type = fild.getGenericType().toString();
            String name;
            if (type.contains(".List")){//子节点 集合

                name = fild.getGenericType().toString();
                name = name.substring(name.lastIndexOf(".")+1,name.length()-1);
                for (Method method : methods) {
                    if (("get"+name.toLowerCase()+"s").equals(method.getName().toLowerCase())){
                        List list = (List) method.invoke(object);
                        if (list != null){
                            for (Object o : list) {
                                Element element = rootElement.addElement(name);
                                write(element,o);
                            }
                        }
                    }
                }
                System.out.println(fild.getName());
                System.out.println(fild.getType());
                System.out.println(fild.getDeclaringClass());
                System.out.println(fild.getGenericType());

            }else if (type.contains(".String")){//属性

                name = fild.getName();
                for (Method method : methods) {
                    String mName = method.getName();
                    if (("get"+name.toLowerCase()).equals(mName.toLowerCase())){
                        String s = (String) method.invoke(object);
                        if (s == null)
                            s = " ";
                        rootElement.addAttribute(fild.getName(),s);
                    }
                }


            }else {//子节点 非集合

                name = fild.getGenericType().toString();
                name = name.substring(name.lastIndexOf(".")+1);
                for (Method method : methods) {
                    String mName = method.getName();
                    if (("get"+name.toLowerCase()).equals(mName.toLowerCase())){
                        Object obj = method.invoke(object);//获得子标签
                        if (obj!=null){
                            Element element = rootElement.addElement(name);
                            write(element,obj);
                        }
                    }
                }

            }
        }
    }


    /**
     * Generate.
     *
     * @param object 数据源，待转化成xml的实体类
     * @param DesPath xml文件生成路径
     * @throws InvocationTargetException the invocation target exception
     * @throws IllegalAccessException    the illegal access exception
     * @throws IOException               the io exception
     */
    public static void generate(Object object,String DesPath)throws InvocationTargetException, IllegalAccessException, IOException {
        String name;
        Class<?> clazz = object.getClass();
        String rootname = clazz.getName();
        rootname = rootname.substring(rootname.lastIndexOf(".")+1);
        System.out.println(rootname);
        Field[] filds = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        Element root = DocumentHelper.createElement(rootname);
        // 创建文档并设置文档的根元素节点
        Document doucment = DocumentHelper.createDocument(root);

        for (Field fild : filds) {
            String type = fild.getGenericType().toString();
            if (type.contains(".String")){
                name = fild.getName();
                for (Method method : methods) {
                    String mName = method.getName();
                    if (("get"+name.toLowerCase()).equals(mName.toLowerCase())){
                        String s = (String) method.invoke(object);
                        if (s == null)
                            s = " ";
                        root.addAttribute(fild.getName(),s);
                        System.out.println("name"+fild.getName()+" value "+s);
                    }
                }
            }else if (type.contains(".List")){
                name = fild.getGenericType().toString();
                name = name.substring(name.lastIndexOf(".")+1,name.length()-1);
                for (Method method : methods) {
                    if (("get"+name.toLowerCase()+"s").equals(method.getName().toLowerCase())){
                        List list = (List) method.invoke(object);
                        for (Object o : list) {
                            Element element = root.addElement(name);

                            write(element,o);
                        }
                    }
                }
            }else {
                name = fild.getGenericType().toString();
                name = name.substring(name.lastIndexOf(".")+1);
                for (Method method : methods) {
                    String mName = method.getName();

                    if (("get"+name.toLowerCase()).equals(mName.toLowerCase())){
                        Object obj = method.invoke(object);//获得子标签
                        if (obj!=null){
                            Element element = root.addElement(name);

                            write(element,obj);
                        }
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
            XMLWriter xmlwriter2 = new XMLWriter(file,outputFormat);
            xmlwriter2.write(doucment);
            xmlwriter2.flush();
            xmlwriter2.close();
        }
    }
}
