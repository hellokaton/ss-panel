package com.sp.service;

import com.blade.jdbc.core.Take;
import com.sp.model.Node;

import java.util.List;

/**
 * Created by biezhi on 2017/2/19.
 */
public interface NodeService {

    List<Node> getNodes(Take take);

    Node byId(Integer id);
}
