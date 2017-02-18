package com.sp.controller.api;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.RestResponse;
import com.sp.model.CheckinLog;
import com.sp.service.CheckinLogService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("/api/checkinLog")
public class CheckinLogController {

	@Inject
	private CheckinLogService checkinLogService;

	@Route(value = "", method = HttpMethod.GET)
	public RestResponse<CheckinLog> checkinLog(){
		return this.checkinLog(1, 10);
	}

	@Route(value = "/:page/:limit", method = HttpMethod.GET)
	public RestResponse checkinLog(@PathParam("page") int page, @PathParam("limit") int limit){
		RestResponse restResponse = new RestResponse<>();

		Take take = new Take(CheckinLog.class);
		take.page(page, limit);

		Paginator<CheckinLog> checkinLogPage = checkinLogService.getCheckinLogPage(take);
		restResponse.setPayload(checkinLogPage);
		restResponse.setSuccess(true);
		return restResponse;
	}

}
