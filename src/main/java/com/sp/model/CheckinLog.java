package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;

//
@Table(name = "ss_checkin_log", pk = "id")
public class CheckinLog implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer user_id;

	private Integer checkin_at;

	private  traffic;

	private Date created_at;

	private Date updated_at;

	public CheckinLog(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCheckin_at() {
		return checkin_at;
	}

	public void setCheckin_at(Integer checkin_at) {
		this.checkin_at = checkin_at;
	}

	public  getTraffic() {
		return traffic;
	}

	public void setTraffic( traffic) {
		this.traffic = traffic;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}


}