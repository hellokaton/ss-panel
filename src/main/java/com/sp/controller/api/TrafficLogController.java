package com.sp.controller.api;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.RestResponse;
import com.sp.model.TrafficLog;
import com.sp.service.TrafficLogService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("/api/trafficLog")
public class TrafficLogController {

	@Inject
	private TrafficLogService trafficLogService;

	@Route(value = "", method = HttpMethod.GET)
	public RestResponse<TrafficLog> trafficLog(){
		return this.trafficLog(1, 10);
	}

	@Route(value = "/:page/:limit", method = HttpMethod.GET)
	public RestResponse trafficLog(@PathParam("page") int page, @PathParam("limit") int limit){
		RestResponse restResponse = new RestResponse<>();

		Take take = new Take(TrafficLog.class);
		take.page(page, limit);

		Paginator<TrafficLog> trafficLogPage = trafficLogService.getTrafficLogPage(take);
		restResponse.setPayload(trafficLogPage);
		restResponse.setSuccess(true);
		return restResponse;
	}

}
