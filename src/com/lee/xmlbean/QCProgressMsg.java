package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class QCProgressMsg {
    private List<QCProgress> qcProgresses;
    public QCProgressMsg() {
        qcProgresses = new ArrayList<>();
    }

    public List<QCProgress> getQcProgresss() {
        return qcProgresses;
    }

    public void setQcProgresss(List<QCProgress> qcProgresses) {
        for (Object qcProgress : qcProgresses) {
            this.qcProgresses.add((QCProgress) qcProgress);
        }
    }
}
