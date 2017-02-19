package com.sp.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.sp.config.SpConst;
import com.sp.controller.BaseController;
import com.sp.ext.Result;
import com.sp.model.Node;
import com.sp.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller("admin")
public class NodeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeController.class);

    @Inject
    private NodeService nodeService;

    @Route(value = "node", method = HttpMethod.GET)
    public String node(Request request) {
        List<Node> nodes = nodeService.all();
        request.attribute("nodes", nodes);
        return this.render("admin/node/index");
    }

    @Route(value = "node/create", method = HttpMethod.GET)
    public String create(Request request) {
        request.attribute("method", SpConst.METHODS);
        return this.render("admin/node/create");
    }

    @Route(value = "node", method = HttpMethod.POST)
    @JSON
    public Result add(@QueryParam String name, @QueryParam String server,
                      @QueryParam String method, @QueryParam int custom_method,
                      @QueryParam Float rate, @QueryParam String info,
                      @QueryParam int type, @QueryParam String status, @QueryParam int sort) {

        if (StringKit.isBlank(name) || StringKit.isBlank(server) ||
                StringKit.isBlank(method) || StringKit.isBlank(info) || StringKit.isBlank(status)) {

            return Result.fail("请确认信息填写完整");
        }

        Node node = new Node();
        node.setName(name);
        node.setServer(server);
        node.setMethod(method);
        node.setCustom_method(custom_method == 1);
        node.setTraffic_rate(rate);
        node.setInfo(info);
        node.setType(type);
        node.setStatus(status);
        node.setSort(sort);

        try {
            nodeService.save(node);
        } catch (Exception e) {
            LOGGER.error("添加节点失败", e);
            return Result.fail("添加失败");
        }
        return Result.ok("节点添加成功");
    }

    @Route(value = "node/:id/edit", method = HttpMethod.GET)
    public String edit(@PathParam Integer id, Request request) {
        Node node = nodeService.byId(id);
        request.attribute("node", node);
        request.attribute("method", SpConst.METHODS);
        return this.render("admin/node/create");
    }

    @Route(value = "node/:id", method = HttpMethod.PUT)
    @JSON
    public Result update(@PathParam Integer id, @QueryParam String name, @QueryParam String server,
                         @QueryParam String method, @QueryParam int custom_method,
                         @QueryParam Float rate, @QueryParam String info,
                         @QueryParam int type, @QueryParam String status, @QueryParam int sort) {

        Node node = new Node();
        node.setId(id);
        node.setName(name);
        node.setServer(server);
        node.setMethod(method);
        node.setCustom_method(custom_method == 1);
        node.setTraffic_rate(rate);
        node.setInfo(info);
        node.setType(type);
        node.setStatus(status);
        node.setSort(sort);

        try {
            nodeService.update(node);
        } catch (Exception e) {
            LOGGER.error("修改节点失败", e);
            return Result.fail("修改失败");
        }
        return Result.ok("修改成功");
    }

    @Route(value = "node/:id", method = HttpMethod.DELETE)
    @JSON
    public Result delete(@PathParam Integer id) {
        try {
            nodeService.delete(id);
        } catch (Exception e) {
            LOGGER.error("删除节点失败", e);
            return Result.fail("删除失败");
        }
        return Result.ok("删除成功");
    }

    @Route(value = "node/:id/delete", method = HttpMethod.GET)
    public void deleteGet(@PathParam Integer id, Response response) {
        try {
            nodeService.delete(id);
            response.go("/admin/node");
        } catch (Exception e) {
            LOGGER.error("删除节点失败", e);
        }
    }

}
