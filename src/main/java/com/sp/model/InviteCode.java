package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "ss_invite_code", pk = "id")
public class InviteCode implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String code;

	private Integer user_id;

	private Integer created_at;

	private Integer updated_at;

	public InviteCode(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Integer created_at) {
		this.created_at = created_at;
	}

	public Integer getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Integer updated_at) {
		this.updated_at = updated_at;
	}
}