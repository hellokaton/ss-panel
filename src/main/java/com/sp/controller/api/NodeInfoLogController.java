package com.sp.controller.api;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.RestResponse;
import com.sp.model.NodeInfoLog;
import com.sp.service.NodeInfoLogService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("/api/nodeInfoLog")
public class NodeInfoLogController {

	@Inject
	private NodeInfoLogService nodeInfoLogService;

	@Route(value = "", method = HttpMethod.GET)
	public RestResponse<NodeInfoLog> nodeInfoLog(){
		return this.nodeInfoLog(1, 10);
	}

	@Route(value = "/:page/:limit", method = HttpMethod.GET)
	public RestResponse nodeInfoLog(@PathParam("page") int page, @PathParam("limit") int limit){
		RestResponse restResponse = new RestResponse<>();

		Take take = new Take(NodeInfoLog.class);
		take.page(page, limit);

		Paginator<NodeInfoLog> nodeInfoLogPage = nodeInfoLogService.getNodeInfoLogPage(take);
		restResponse.setPayload(nodeInfoLogPage);
		restResponse.setSuccess(true);
		return restResponse;
	}

}
