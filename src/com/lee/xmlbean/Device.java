package com.lee.xmlbean;

/**
 * Created by lijie on 2017/6/13.
 */
public class Device {
    private String DevBornDate;
    private String DevDieDate;
    private String DevStatus;
    private String Id;
    private String Name;
    private String subDevId;
    private String subDevName;
    private DataSet dataSet;
    private J1939 j1939;
    private QCSet qcSet;
    private RealTimeSet realTimeSet;

    public Device() {
    }

    public String getDevBornDate() {
        return DevBornDate;
    }

    public void setDevBornDate(Object devBornDate) {
        DevBornDate = (String) devBornDate;
    }

    public String getDevDieDate() {
        return DevDieDate;
    }

    public void setDevDieDate(Object devDieDate) {
        DevDieDate = (String) devDieDate;
    }

    public String getDevStatus() {
        return DevStatus;
    }

    public void setDevStatus(Object devStatus) {
        DevStatus = (String) devStatus;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSubDevId() {
        return subDevId;
    }

    public void setSubDevId(Object subDevId) {
        this.subDevId = (String) subDevId;
    }

    public String getSubDevName() {
        return subDevName;
    }

    public void setSubDevName(Object subDevName) {
        this.subDevName = (String) subDevName;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(Object dataSet) {
        this.dataSet = (DataSet) dataSet;
    }

    public J1939 getJ1939() {
        return j1939;
    }

    public void setJ1939(Object j1939) {
        this.j1939 = (J1939) j1939;
    }

    public QCSet getQcSet() {
        return qcSet;
    }

    public void setQcSet(Object qcSet) {
        this.qcSet = (QCSet) qcSet;
    }

    public RealTimeSet getRealTimeSet() {
        return realTimeSet;
    }

    public void setRealTimeSet(Object realTimeSet) {
        this.realTimeSet = (RealTimeSet) realTimeSet;
    }

    @Override
    public String toString() {
        return "Device{" +
                "DevBornDate='" + DevBornDate + '\'' +
                ", DevDieDate='" + DevDieDate + '\'' +
                ", DevStatus='" + DevStatus + '\'' +
                ", Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", subDevId='" + subDevId + '\'' +
                ", subDevName='" + subDevName + '\'' +
                ", dataSet=" + dataSet +
                ", j1939=" + j1939 +
                ", qcSet=" + qcSet +
                ", realTimeSet=" + realTimeSet +
                '}';
    }
}
