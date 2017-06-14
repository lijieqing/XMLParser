package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class RealTimeSet {
    private List<RealTimeParam> realTimeParams;
    public RealTimeSet() {
        realTimeParams = new ArrayList<>();
    }

    public List<RealTimeParam> getRealTimeParams() {
        return realTimeParams;
    }

    public void setRealTimeParams(List<RealTimeParam> realTimeParams) {
        for (Object realTimeParam : realTimeParams) {
            this.realTimeParams.add((RealTimeParam) realTimeParam);
        }
    }

    @Override
    public String toString() {
        return "RealTimeSet{" +
                "realTimeParams=" + realTimeParams.size() +
                '}';
    }
}
