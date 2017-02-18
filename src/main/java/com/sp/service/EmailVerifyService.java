package com.sp.service;

import java.util.List;

import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.EmailVerify;

public interface EmailVerifyService {
	
	EmailVerify getEmailVerifyById(Integer id);

	List<EmailVerify> getEmailVerifyList(Take take);
	
	Paginator<EmailVerify> getEmailVerifyPage(Take take);

	void update(EmailVerify emailVerify) throws Exception;

	void save(EmailVerify emailVerify) throws Exception;
	
	void delete(Integer id) throws Exception;
		
}
