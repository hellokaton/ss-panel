package com.sp.controller.api;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.RestResponse;
import com.sp.model.Config;
import com.sp.service.ConfigService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("/api/config")
public class ConfigController {

	@Inject
	private ConfigService configService;

	@Route(value = "", method = HttpMethod.GET)
	public RestResponse<Config> config(){
		return this.config(1, 10);
	}

	@Route(value = "/:page/:limit", method = HttpMethod.GET)
	public RestResponse config(@PathParam("page") int page, @PathParam("limit") int limit){
		RestResponse restResponse = new RestResponse<>();

		Take take = new Take(Config.class);
		take.page(page, limit);

		Paginator<Config> configPage = configService.getConfigPage(take);
		restResponse.setPayload(configPage);
		restResponse.setSuccess(true);
		return restResponse;
	}

}
