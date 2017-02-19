package com.sp.dto;

import com.sp.model.Node;
import com.sp.model.NodeInfoLog;

/**
 * Created by biezhi on 2017/2/19.
 */
public class NodeDto extends Node {

    private NodeInfoLog log;

    public String getNodeLoad(){
        if(null == log){
            return "暂无数据";
        }
        return log.getLoad();
    }

    public NodeInfoLog getLastNodeInfoLog(){
        return log;
    }

    public void setLog(NodeInfoLog log) {
        this.log = log;
    }

}
