package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "sp_log", pk = "id")
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String type;

	private String msg;

	private Integer created_time;

	public Log(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Integer created_time) {
		this.created_time = created_time;
	}


}