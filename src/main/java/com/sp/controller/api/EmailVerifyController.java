package com.sp.controller.api;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.RestResponse;
import com.sp.model.EmailVerify;
import com.sp.service.EmailVerifyService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController("/api/emailVerify")
public class EmailVerifyController {

	@Inject
	private EmailVerifyService emailVerifyService;

	@Route(value = "", method = HttpMethod.GET)
	public RestResponse<EmailVerify> emailVerify(){
		return this.emailVerify(1, 10);
	}

	@Route(value = "/:page/:limit", method = HttpMethod.GET)
	public RestResponse emailVerify(@PathParam("page") int page, @PathParam("limit") int limit){
		RestResponse restResponse = new RestResponse<>();

		Take take = new Take(EmailVerify.class);
		take.page(page, limit);

		Paginator<EmailVerify> emailVerifyPage = emailVerifyService.getEmailVerifyPage(take);
		restResponse.setPayload(emailVerifyPage);
		restResponse.setSuccess(true);
		return restResponse;
	}

}
