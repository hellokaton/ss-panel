package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "user_token", pk = "id")
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String token;

	private Integer user_id;

	private Integer create_time;

	private Integer expire_time;

	public Token(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Integer create_time) {
		this.create_time = create_time;
	}

	public Integer getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Integer expire_time) {
		this.expire_time = expire_time;
	}


}