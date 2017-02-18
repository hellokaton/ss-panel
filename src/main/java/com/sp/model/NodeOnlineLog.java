package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "ss_node_online_log", pk = "id")
public class NodeOnlineLog implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer node_id;

	private Integer online_user;

	private Integer log_time;

	public NodeOnlineLog(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNode_id() {
		return node_id;
	}

	public void setNode_id(Integer node_id) {
		this.node_id = node_id;
	}

	public Integer getOnline_user() {
		return online_user;
	}

	public void setOnline_user(Integer online_user) {
		this.online_user = online_user;
	}

	public Integer getLog_time() {
		return log_time;
	}

	public void setLog_time(Integer log_time) {
		this.log_time = log_time;
	}


}