package com.sp.controller.api;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.RestResponse;
import com.sp.model.Log;
import com.sp.service.LogService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("/api/log")
public class LogController {

	@Inject
	private LogService logService;

	@Route(value = "", method = HttpMethod.GET)
	public RestResponse<Log> log(){
		return this.log(1, 10);
	}

	@Route(value = "/:page/:limit", method = HttpMethod.GET)
	public RestResponse log(@PathParam("page") int page, @PathParam("limit") int limit){
		RestResponse restResponse = new RestResponse<>();

		Take take = new Take(Log.class);
		take.page(page, limit);

		Paginator<Log> logPage = logService.getLogPage(take);
		restResponse.setPayload(logPage);
		restResponse.setSuccess(true);
		return restResponse;
	}

}
