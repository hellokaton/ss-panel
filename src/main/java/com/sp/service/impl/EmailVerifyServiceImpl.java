package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.kit.StringKit;
import com.sp.model.EmailVerify;
import com.sp.service.EmailVerifyService;

@Service
public class EmailVerifyServiceImpl implements EmailVerifyService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public boolean checkVerifyCode(String email, String verifycode) {
		if(StringKit.isNotBlank(email) && StringKit.isNotBlank(verifycode)){
			EmailVerify emailVerify = new EmailVerify();
			emailVerify.setEmail(email);

		}
		return false;
	}

	@Override
	public void sendVerification(String email) {

	}
}
