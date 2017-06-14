package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class PG {
    private String Dir;
    private String Len;
    private String PGN;
    private String Prio;
    private String Rate;
    private String Req;
    private String ReqCyc;
    private String SA;
    private String Type;
    private List<SP> sps;
    public PG() {
        sps = new ArrayList<>();
    }

    public String getDir() {
        return Dir;
    }

    public void setDir(Object dir) {
        Dir = (String) dir;
    }

    public String getLen() {
        return Len;
    }

    public void setLen(Object len) {
        Len = (String) len;
    }

    public String getPGN() {
        return PGN;
    }

    public void setPGN(Object PGN) {
        this.PGN = (String) PGN;
    }

    public String getPrio() {
        return Prio;
    }

    public void setPrio(Object prio) {
        Prio = (String) prio;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(Object rate) {
        Rate = (String) rate;
    }

    public String getReq() {
        return Req;
    }

    public void setReq(Object req) {
        Req = (String) req;
    }

    public String getReqCyc() {
        return ReqCyc;
    }

    public void setReqCyc(Object reqCyc) {
        ReqCyc = (String) reqCyc;
    }

    public String getSA() {
        return SA;
    }

    public void setSA(Object SA) {
        this.SA = (String) SA;
    }

    public String getType() {
        return Type;
    }

    public void setType(Object type) {
        Type = (String) type;
    }

    public List<SP> getSps() {
        return sps;
    }

    public void setSps(List<SP> sps) {
        for (Object sp : sps) {
            this.sps.add((SP) sp);
        }
    }
}
