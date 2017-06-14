package com.lee.xmlbean;

/**
 * Created by lijie on 2017/6/13.
 */
public class QCParam {
    private String Param;
    private String ValidAvg;
    private String ValidMax;
    private String ValidMin;
    public QCParam() {
    }

    public String getParam() {
        return Param;
    }

    public void setParam(Object param) {
        Param = (String) param;
    }

    public String getValidAvg() {
        return ValidAvg;
    }

    public void setValidAvg(Object validAvg) {
        ValidAvg = (String) validAvg;
    }

    public String getValidMax() {
        return ValidMax;
    }

    public void setValidMax(Object validMax) {
        ValidMax = (String) validMax;
    }

    public String getValidMin() {
        return ValidMin;
    }

    public void setValidMin(Object validMin) {
        ValidMin = (String) validMin;
    }
}
