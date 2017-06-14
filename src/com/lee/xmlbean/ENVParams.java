package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class ENVParams {
    private List<ENVParam> envParams;
    public ENVParams() {
        envParams = new ArrayList<>();
    }

    public List<ENVParam> getEnvParams() {
        return envParams;
    }

    public void setEnvParams(List<ENVParam> envParams) {
        for (Object envParam : envParams) {
            this.envParams.add((ENVParam) envParam);
        }
    }
}
