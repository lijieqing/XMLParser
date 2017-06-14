package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class DataSet {
    private List<DSItem> dsItems;
    public DataSet() {
        dsItems = new ArrayList<>();
    }

    public List<DSItem> getDsItems() {
        return dsItems;
    }

    public void setDsItems(List<DSItem> dsItems) {
        this.dsItems.addAll(dsItems);
        //this.dsItems = dsItems;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "dsItems=" + dsItems.size() +
                '}';
    }
}
