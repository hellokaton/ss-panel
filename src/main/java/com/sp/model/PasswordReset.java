package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "ss_password_reset", pk = "id")
public class PasswordReset implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String email;

	private String token;

	private Integer init_time;

	private Integer expire_time;

	public PasswordReset(){}

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

	public Integer getInit_time() {
		return init_time;
	}

	public void setInit_time(Integer init_time) {
		this.init_time = init_time;
	}

	public Integer getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Integer expire_time) {
		this.expire_time = expire_time;
	}


}