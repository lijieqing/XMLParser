package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/14.
 */
public class QCSet {
    private List<QCItem> qcItems;

    public QCSet() {
        qcItems = new ArrayList<>();
    }

    public List<QCItem> getQcItems() {
        return qcItems;
    }

    public void setQcItems(List<QCItem> qcItems) {
        this.qcItems.addAll(qcItems);
    }

    @Override
    public String toString() {
        return "QCSet{" +
                "qcItems=" + qcItems.size() +
                '}';
    }
}
