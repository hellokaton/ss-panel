package com.sp.interceptor;

import com.blade.ioc.annotation.Inject;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.Intercept;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.interceptor.Interceptor;
import com.sp.config.SpConst;
import com.sp.model.User;
import com.sp.service.UserService;
import com.sp.utils.SessionUtils;
import com.sp.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercept
public class BaseInterceptor implements Interceptor {
	
	private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
	
	@Inject
	private UserService userService;
	
	@Override
	public boolean before(Request request, Response response) {
		
		LOGGE.info("UA >>> " + request.userAgent());
		LOGGE.info("用户访问地址 >>> " + request.raw().getRequestURI() + ", 来路地址  >>> " + Utils.getIpAddr(request));

		User user = SessionUtils.getLoginUser();
		if(null == user){
			String uid = SessionUtils.getCookie(request, SpConst.USER_IN_COOKIE);
			if(StringKit.isNotBlank(uid)){
				user = userService.getUserById(Integer.valueOf(uid));
				SessionUtils.setLoginUser(request.session(), user);
			} else {
				response.removeCookie(SpConst.USER_IN_COOKIE);
			}
		}
		return true;
	}
	

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}
	
}
