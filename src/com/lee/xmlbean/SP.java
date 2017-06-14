package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class SP {
    private String Bits;
    private String Bytes;
    private String Off;
    private String Ref;
    private String Res;
    private String SBit;
    private String SByte;
    private String SPN;
    private String Type;
    private List<DTC> dtcs;

    public SP() {
        dtcs = new ArrayList<>();
    }

    public String getBits() {
        return Bits;
    }

    public void setBits(Object bits) {
        Bits = (String) bits;
    }

    public String getBytes() {
        return Bytes;
    }

    public void setBytes(Object bytes) {
        Bytes = (String) bytes;
    }

    public String getOff() {
        return Off;
    }

    public void setOff(Object off) {
        Off = (String) off;
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(Object ref) {
        Ref = (String) ref;
    }

    public String getRes() {
        return Res;
    }

    public void setRes(Object res) {
        Res = (String) res;
    }

    public String getSBit() {
        return SBit;
    }

    public void setSBit(Object SBit) {
        this.SBit = (String) SBit;
    }

    public String getSByte() {
        return SByte;
    }

    public void setSByte(Object SByte) {
        this.SByte = (String) SByte;
    }

    public String getSPN() {
        return SPN;
    }

    public void setSPN(Object SPN) {
        this.SPN = (String) SPN;
    }

    public String getType() {
        return Type;
    }

    public void setType(Object type) {
        Type = (String) type;
    }

    public List<DTC> getDtcs() {
        return dtcs;
    }

    public void setDtcs(List<DTC> dtcs) {
        for (Object dtc : dtcs) {
            this.dtcs.add((DTC) dtc);
        }
    }
}
