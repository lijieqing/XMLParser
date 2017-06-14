package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class DSItem {
    private String DataType;
    private String DecLen;
    private String LinkTo;
    private String Name;
    private String Unit;
    private String Value;
    private String Rows;
    private List<Data> datas;

    public DSItem() {
        datas = new ArrayList<>();
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(Object dataType) {
        DataType = (String) dataType;
    }

    public String getDecLen() {
        return DecLen;
    }

    public void setDecLen(Object decLen) {
        DecLen = (String) decLen;
    }

    public String getLinkTo() {
        return LinkTo;
    }

    public void setLinkTo(Object linkTo) {
        LinkTo = (String) linkTo;
    }

    public String getName() {
        return Name;
    }

    public void setName(Object name) {
        Name = (String) name;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(Object unit) {
        Unit = (String) unit;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = (String) value;
    }

    public String getRows() {
        return Rows;
    }

    public void setRows(Object rows) {
        Rows = (String) rows;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas.addAll(datas);
    }
}
