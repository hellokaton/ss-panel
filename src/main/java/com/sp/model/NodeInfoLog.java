package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "ss_node_info_log", pk = "id")
public class NodeInfoLog implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer node_id;

	private Float uptime;

	private String load;

	private Integer log_time;

	public NodeInfoLog(){}

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

	public Float getUptime() {
		return uptime;
	}

	public void setUptime(Float uptime) {
		this.uptime = uptime;
	}

	public String getLoad() {
		return load;
	}

	public void setLoad(String load) {
		this.load = load;
	}

	public Integer getLog_time() {
		return log_time;
	}

	public void setLog_time(Integer log_time) {
		this.log_time = log_time;
	}


}