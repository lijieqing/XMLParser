package lee.hua.xmlparse.xml;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/7.
 */
public abstract class XMLBase {
    protected String name;
    protected List<XMLAttribute> XMLAttributes;

    public XMLBase(String name) {
        this.name = name;
        XMLAttributes = new ArrayList<>();
    }

    public boolean addAttribute(XMLAttribute XMLAttribute) {
        if (XMLAttribute != null && !XMLAttributes.contains(XMLAttribute)) {
            XMLAttributes.add(XMLAttribute);
            return true;
        }
        return false;
    }

    public boolean removeAttribute(XMLAttribute XMLAttribute) {
        if (XMLAttribute != null && XMLAttributes.contains(XMLAttribute)) {
            XMLAttributes.add(XMLAttribute);
            return true;
        }
        return false;
    }

    public abstract boolean addKids(XMLBase base);

    public abstract boolean removeKids(XMLBase base);

    public abstract void showKids();

    //通过 反射将读取出的xmlbase 实体类转换为 com.lee.xmlbean 优雅 哈哈哈
    @SuppressWarnings("Duplicates")
    public abstract Object transform();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XMLAttribute> getXMLAttributes() {
        return XMLAttributes;
    }

    public void setXMLAttributes(List<XMLAttribute> XMLAttributes) {
        this.XMLAttributes = XMLAttributes;
    }

    protected void valueFormat(String type, Object o, Object value, Field field) throws IllegalAccessException {
        if (type.contains(".String")) {
            XMLAttribute attr = (XMLAttribute) value;
            field.set(o, attr.getValues());
        } else if (type.contains("int")) {
            XMLAttribute attr = (XMLAttribute) value;
            Integer values;
            try {
                values = Integer.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                values = 0;
            }
            field.setInt(o, values);
        } else if (type.contains(".Integer") ){
            XMLAttribute attr = (XMLAttribute) value;
            Integer values;
            try {
                values = Integer.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                values = 0;
            }
            field.set(o, values);
        }else if (type.contains("float")) {
            XMLAttribute attr = (XMLAttribute) value;
            Float values;
            try {
                values = Float.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0.0f;
                e.printStackTrace();
            }
            field.setFloat(o, values);
        } else if (type.contains(".Float")) {
            XMLAttribute attr = (XMLAttribute) value;
            Float values;
            try {
                values = Float.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0.0f;
                e.printStackTrace();
            }
            field.set(o, values);
        }else if (type.contains("byte")) {
            XMLAttribute attr = (XMLAttribute) value;
            Byte values;
            try {
                values = Byte.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0;
                e.printStackTrace();
            }
            field.setByte(o, values);
        } else if (type.contains(".Byte")) {
            XMLAttribute attr = (XMLAttribute) value;
            Byte values;
            try {
                values = Byte.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0;
                e.printStackTrace();
            }
            field.set(o, values);
        } else if (type.contains("double")) {
            XMLAttribute attr = (XMLAttribute) value;
            Double values;
            try {
                values = Double.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0.0;
                e.printStackTrace();
            }
            field.setDouble(o, values);
        } else if (type.contains(".Double")) {
            XMLAttribute attr = (XMLAttribute) value;
            Double values;
            try {
                values = Double.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0.0;
                e.printStackTrace();
            }
            field.set(o, values);
        } else if (type.contains("long")) {
            XMLAttribute attr = (XMLAttribute) value;
            Long values;
            try {
                values = Long.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0L;
                e.printStackTrace();
            }
            field.setLong(o, values);
        } else if (type.contains(".Long")) {
            XMLAttribute attr = (XMLAttribute) value;
            Long values;
            try {
                values = Long.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0L;
                e.printStackTrace();
            }
            field.set(o, values);
        }else if (type.contains("short")) {
            XMLAttribute attr = (XMLAttribute) value;
            Short values;
            try {
                values = Short.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0;
                e.printStackTrace();
            }
            field.setShort(o, values);
        } else if (type.contains(".Short")) {
            XMLAttribute attr = (XMLAttribute) value;
            Short values;
            try {
                values = Short.valueOf(attr.getValues());
            } catch (NumberFormatException e) {
                values = 0;
                e.printStackTrace();
            }
            field.set(o, values);
        }else if (type.contains("boolean")) {
            XMLAttribute attr = (XMLAttribute) value;
            Boolean values;
            values = Boolean.valueOf(attr.getValues());
            field.setBoolean(o, values);
        }else if (type.contains(".Boolean")) {
            XMLAttribute attr = (XMLAttribute) value;
            Boolean values;
            values = Boolean.valueOf(attr.getValues());
            field.set(o, values);
        }
    }
}
