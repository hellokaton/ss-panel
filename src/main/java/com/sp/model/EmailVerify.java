package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "sp_email_verify", pk = "id")
public class EmailVerify implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String email;

	private String token;

	private Integer expire_at;

	public EmailVerify(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getExpire_at() {
		return expire_at;
	}

	public void setExpire_at(Integer expire_at) {
		this.expire_at = expire_at;
	}


}