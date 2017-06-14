package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class QCParams {
    private List<QCParam> qcParams;
    public QCParams() {
        qcParams = new ArrayList<>();
    }

    public List<QCParam> getQcParams() {
        return qcParams;
    }

    public void setQcParams(List<QCParam> qcParams) {
        for (Object qcParam : qcParams) {
            this.qcParams.add((QCParam) qcParam);
        }
    }
}
