package com.sp.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.EmailVerify;
import com.sp.exception.TipException;
import com.sp.service.EmailVerifyService;

@Service
public class EmailVerifyServiceImpl implements EmailVerifyService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public EmailVerify getEmailVerifyById(Integer id) {
		if(null == id){
			return null;
		}
		return activeRecord.byId(EmailVerify.class, id);
	}

	@Override
	public List<EmailVerify> getEmailVerifyList(Take take) {
		if(null != take){
			if(null != take.getPageRow()){
				return this.getEmailVerifyPage(take).getList();
			}
			return activeRecord.list(take);
		}
		return null;
	}
	
	@Override
	public Paginator<EmailVerify> getEmailVerifyPage(Take take) {
		if(null != take){
			return activeRecord.page(take);
		}
		return null;
	}
	
	@Override
	public void save(EmailVerify emailVerify) throws Exception {
		if(null == emailVerify){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.insert(emailVerify);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(EmailVerify emailVerify) throws Exception {
		if(null == emailVerify){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.update(emailVerify);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		if(null == id){
			throw new TipException("主键为空");
		}
		try {
			activeRecord.delete(EmailVerify.class, id);
		} catch (Exception e){
			throw e;
		}
	}
		
}
