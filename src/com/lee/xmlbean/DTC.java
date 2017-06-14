package com.lee.xmlbean;

/**
 * Created by lijie on 2017/6/13.
 */
public class DTC {
    private String FMI;
    private String Icon;
    private String MsgId;

    public DTC() {
    }

    public String getFMI() {
        return FMI;
    }

    public void setFMI(Object FMI) {
        this.FMI = (String) FMI;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(Object icon) {
        Icon = (String) icon;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(Object msgId) {
        MsgId = (String) msgId;
    }

    @Override
    public String toString() {
        return "DTC{" +
                "FMI='" + FMI + '\'' +
                ", Icon='" + Icon + '\'' +
                ", MsgId='" + MsgId + '\'' +
                '}';
    }
}
