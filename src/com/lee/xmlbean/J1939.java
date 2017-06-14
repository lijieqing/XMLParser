package com.lee.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijie on 2017/6/13.
 */
public class J1939 {
    private String Cycle;
    private String NodeAddr;
    private String TaskPrio;
    private List<PG> pgs;
    public J1939() {
        pgs = new ArrayList<>();
    }

    public String getCycle() {
        return Cycle;
    }

    public void setCycle(Object cycle) {
        Cycle = (String) cycle;
    }

    public String getNodeAddr() {
        return NodeAddr;
    }

    public void setNodeAddr(Object nodeAddr) {
        NodeAddr = (String) nodeAddr;
    }

    public String getTaskPrio() {
        return TaskPrio;
    }

    public void setTaskPrio(Object taskPrio) {
        TaskPrio = (String) taskPrio;
    }

    public List<PG> getPgs() {
        return pgs;
    }

    public void setPgs(List<PG> pgs) {
        for (Object pg : pgs) {
            this.pgs.add((PG) pg);
        }
    }

    @Override
    public String toString() {
        return "J1939{" +
                "Cycle='" + Cycle + '\'' +
                ", NodeAddr='" + NodeAddr + '\'' +
                ", TaskPrio='" + TaskPrio + '\'' +
                ", pgs=" + pgs.size() +
                '}';
    }
}
