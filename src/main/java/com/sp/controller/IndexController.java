package com.sp.controller;


import com.blade.Blade;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.kit.DateKit;
import com.blade.kit.FileKit;
import com.blade.kit.PatternKit;
import com.blade.kit.StringKit;
import com.blade.kit.json.JSONObject;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.view.ModelAndView;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

	@Route(value = "/", method = HttpMethod.GET)
	public ModelAndView index(Request request, Response response){
		return new ModelAndView("index.html");
	}

}
