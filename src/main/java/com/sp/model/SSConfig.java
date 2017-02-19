package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "sp_config", pk = "id")
public class SSConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String key_;

	private String value_;

	private Integer created_at;

	private Integer updated_at;

	public SSConfig(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey_() {
		return key_;
	}

	public void setKey_(String key_) {
		this.key_ = key_;
	}

	public String getValue_() {
		return value_;
	}

	public void setValue_(String value_) {
		this.value_ = value_;
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