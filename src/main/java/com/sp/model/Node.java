package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "ss_node", pk = "id")
public class Node implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Integer type;

	private String server;

	private String method;

	private Boolean custom_method;

	private Float traffic_rate;

	private String info;

	private String status;

	private Integer offset;

	private Integer sort;

	public Node(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Boolean getCustom_method() {
		return custom_method;
	}

	public void setCustom_method(Boolean custom_method) {
		this.custom_method = custom_method;
	}

	public Float getTraffic_rate() {
		return traffic_rate;
	}

	public void setTraffic_rate(Float traffic_rate) {
		this.traffic_rate = traffic_rate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


}