package com.lee.xmlbean;

/**
 * Created by lijie on 2017/6/13.
 */
public class Msgs {
    private String AbortMsg;
    private String NotReadyMsg;
    private String OkMsg;
    private String ReadyMsg;
    private QCProgressMsg qcProgressMsg;
    private QCErrMsg qcErrMsg;
    public Msgs() {
    }

    public String getAbortMsg() {
        return AbortMsg;
    }

    public void setAbortMsg(Object abortMsg) {
        AbortMsg = (String) abortMsg;
    }

    public String getNotReadyMsg() {
        return NotReadyMsg;
    }

    public void setNotReadyMsg(Object notReadyMsg) {
        NotReadyMsg = (String) notReadyMsg;
    }

    public String getOkMsg() {
        return OkMsg;
    }

    public void setOkMsg(Object okMsg) {
        OkMsg = (String) okMsg;
    }

    public String getReadyMsg() {
        return ReadyMsg;
    }

    public void setReadyMsg(Object readyMsg) {
        ReadyMsg = (String) readyMsg;
    }

    public QCProgressMsg getQcProgressMsg() {
        return qcProgressMsg;
    }

    public void setQcProgressMsg(Object qcProgressMsg) {
        this.qcProgressMsg = (QCProgressMsg) qcProgressMsg;
    }

    public QCErrMsg getQcErrMsg() {
        return qcErrMsg;
    }

    public void setQcErrMsg(Object qcErrMsg) {
        this.qcErrMsg = (QCErrMsg) qcErrMsg;
    }
}
