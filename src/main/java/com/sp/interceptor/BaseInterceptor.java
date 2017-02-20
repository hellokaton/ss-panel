package com.sp.interceptor;

import com.blade.ioc.annotation.Inject;
import com.blade.kit.PatternKit;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.Intercept;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.interceptor.Interceptor;
import com.sp.config.SpConst;
import com.sp.dto.LoginUser;
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

		String uri = request.uri();

		LOGGE.info("UA >>> " + request.userAgent());
		LOGGE.info("用户访问地址:{} 来路地址:{}", uri, Utils.getIpAddr(request));

		LoginUser loginUser = SessionUtils.getLoginUser();
		if(null == loginUser){
			String uid = SessionUtils.getCookie(request, SpConst.USER_IN_COOKIE);
			if(StringKit.isNotBlank(uid) && PatternKit.isNumber(uid)){
				User user = userService.getUserById(Integer.valueOf(uid));
				SessionUtils.setLoginUser(request.session(), new LoginUser(user));
			} else {
				response.removeCookie(SpConst.USER_IN_COOKIE);
			}
		}

		if(uri.startsWith("/user") || uri.startsWith("/admin")){
			if(null == loginUser){
				response.go("/auth/login");
				return false;
			}
		}
		return true;
	}
	

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}
	
}
