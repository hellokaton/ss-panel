package com.sp.controller.api;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.RestResponse;
import com.sp.model.NodeOnlineLog;
import com.sp.service.NodeOnlineLogService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("/api/nodeOnlineLog")
public class NodeOnlineLogController {

	@Inject
	private NodeOnlineLogService nodeOnlineLogService;

	@Route(value = "", method = HttpMethod.GET)
	public RestResponse<NodeOnlineLog> nodeOnlineLog(){
		return this.nodeOnlineLog(1, 10);
	}

	@Route(value = "/:page/:limit", method = HttpMethod.GET)
	public RestResponse nodeOnlineLog(@PathParam("page") int page, @PathParam("limit") int limit){
		RestResponse restResponse = new RestResponse<>();

		Take take = new Take(NodeOnlineLog.class);
		take.page(page, limit);

		Paginator<NodeOnlineLog> nodeOnlineLogPage = nodeOnlineLogService.getNodeOnlineLogPage(take);
		restResponse.setPayload(nodeOnlineLogPage);
		restResponse.setSuccess(true);
		return restResponse;
	}

}
