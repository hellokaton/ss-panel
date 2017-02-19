package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.sp.model.Node;
import com.sp.service.NodeService;

import java.util.List;

/**
 * Created by biezhi on 2017/2/19.
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Inject
    private ActiveRecord activeRecord;

    @Override
    public List<Node> getNodes(Take take) {
        if(null != take){
            return activeRecord.list(take);
        }
        return null;
    }

    @Override
    public Node byId(Integer id) {
        if(null != id){
            return activeRecord.byId(Node.class, id);
        }
        return null;
    }

    @Override
    public List<Node> all() {
        return activeRecord.list(new Node());
    }

    @Override
    public void save(Node node) {
        if(null != node){
            activeRecord.insert(node);
        }
    }

    @Override
    public void update(Node node) {
        if(null != node && null != node.getId()){
            activeRecord.update(node);
        }
    }

    @Override
    public void delete(Integer id) {
        if(null != id){
            activeRecord.delete(Node.class, id);
        }
    }
}
