package lee.hua.xmlparse.xml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import lee.hua.xmlparse.annotation.XmlBean;

/**
 * Created by lijie on 2017/6/14.
 */
public class Globals {

    private Globals() {
    }

    /**
     * XML标签类路径集合，key是XML标签名，value是XMLBean的class路径
     */
    private static Map<String, String> xmlNameClassPathMap = new HashMap<>();

    /**
     * 根据标签名获取对应的Class Name
     *
     * @param xmlBeanName 标签名
     * @return class name
     */
    public static String getBeanClass(String xmlBeanName) {
        if (xmlNameClassPathMap.containsKey(xmlBeanName)) {
            return xmlNameClassPathMap.get(xmlBeanName);
        }
        return "";
    }

    public static void classParse(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //获取指定包下的包路径
        Enumeration<URL> urls = classLoader.getResources(packageName.replace(".", "/"));
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url.getPath());
            File file = new File(url.getPath());
            searchClass(file, packageName, xmlNameClassPathMap);
        }

        System.out.println(xmlNameClassPathMap);
    }

    /**
     * 递归搜索指定路径文件下的类文件
     *
     * @param file         指定文件
     * @param packageName  当前包名
     * @param nameClassMap XML标签名 与 类路径的map集合
     * @throws ClassNotFoundException
     */
    private static void searchClass(File file, String packageName, Map<String, String> nameClassMap) throws ClassNotFoundException {
        if (file.isDirectory()) {
            //是目录，遍历子文件
            File[] files = file.listFiles();
            StringBuilder sb;
            for (File f : files) {
                if (f.isDirectory()) {
                    //如果存在目录，变更类路径
                    sb = new StringBuilder(packageName).append(".").append(f.getName());
                } else {
                    //不是目录，使用原类路径
                    sb = new StringBuilder(packageName);
                }
                //再次进行搜索
                searchClass(f, sb.toString(), nameClassMap);
            }
        } else {
            //不是目录，获取文件名
            String fname = file.getName();
            StringBuilder sb;
            if (fname.endsWith(".class")) {
                //如果是class文件，去掉尾巴
                fname = fname.replace(".class", "");
                //组装包路径
                sb = new StringBuilder(packageName).append(".").append(fname);
                System.out.println("扫描类信息-" + sb.toString());
                Class<?> clazz = Class.forName(sb.toString());
                XmlBean xmlBean = clazz.getAnnotation(XmlBean.class);
                //通过反射获取XmlBean注解，如果存在，加入map中
                if (xmlBean != null) {
                    if (!"".equals(xmlBean.name().trim())) {
                        nameClassMap.put(xmlBean.name(), sb.toString());
                    } else {
                        nameClassMap.put(fname, sb.toString());
                    }
                }
            }
        }

    }
}
