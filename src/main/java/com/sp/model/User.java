package com.sp.model;

import java.io.Serializable;
import java.util.Date;
import com.blade.jdbc.annotation.Table;
import com.blade.kit.DateKit;
import com.sp.ext.Functions;

//
@Table(name = "sp_user", pk = "id")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String user_name;

	private String email;

	private String pass;

	private String passwd;

	private Integer t;

	private Integer u;

	private Integer d;

	private Integer transfer_enable;

	private Integer port;

	private String protocol;

	private String obfs;

	private Integer switched;

	private Integer enable;

	private Integer type;

	private Integer last_get_gift_time;

	private Integer last_check_in_time;

	private Integer last_rest_pass_time;

	private Integer reg_date;

	private Integer invite_num;

	private Integer is_admin;

	private Integer ref_by;

	private Integer expire_time;

	private String method;

	private Integer is_email_verify;

	private String reg_ip;

	public User(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getT() {
		return t;
	}

	public void setT(Integer t) {
		this.t = t;
	}

	public Integer getU() {
		return u;
	}

	public void setU(Integer u) {
		this.u = u;
	}

	public Integer getD() {
		return d;
	}

	public void setD(Integer d) {
		this.d = d;
	}

	public Integer getTransfer_enable() {
		return transfer_enable;
	}

	public void setTransfer_enable(Integer transfer_enable) {
		this.transfer_enable = transfer_enable;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getObfs() {
		return obfs;
	}

	public void setObfs(String obfs) {
		this.obfs = obfs;
	}

	public Integer getSwitched() {
		return switched;
	}

	public void setSwitched(Integer switched) {
		this.switched = switched;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLast_get_gift_time() {
		return last_get_gift_time;
	}

	public void setLast_get_gift_time(Integer last_get_gift_time) {
		this.last_get_gift_time = last_get_gift_time;
	}

	public Integer getLast_check_in_time() {
		return last_check_in_time;
	}

	public void setLast_check_in_time(Integer last_check_in_time) {
		this.last_check_in_time = last_check_in_time;
	}

	public Integer getLast_rest_pass_time() {
		return last_rest_pass_time;
	}

	public void setLast_rest_pass_time(Integer last_rest_pass_time) {
		this.last_rest_pass_time = last_rest_pass_time;
	}

	public Integer getReg_date() {
		return reg_date;
	}

	public void setReg_date(Integer reg_date) {
		this.reg_date = reg_date;
	}

	public Integer getInvite_num() {
		return invite_num;
	}

	public void setInvite_num(Integer invite_num) {
		this.invite_num = invite_num;
	}

	public Integer getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(Integer is_admin) {
		this.is_admin = is_admin;
	}

	public Integer getRef_by() {
		return ref_by;
	}

	public void setRef_by(Integer ref_by) {
		this.ref_by = ref_by;
	}

	public Integer getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Integer expire_time) {
		this.expire_time = expire_time;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getIs_email_verify() {
		return is_email_verify;
	}

	public void setIs_email_verify(Integer is_email_verify) {
		this.is_email_verify = is_email_verify;
	}

	public String getReg_ip() {
		return reg_ip;
	}

	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
	}

	/**
	 * 检查是否已经签到
	 * @return
	 */
	public boolean isAbleToCheckin(){
		Integer ct = Integer.valueOf(Functions.config("checkinTime"));
		if(last_check_in_time + ct < DateKit.getCurrentUnixTime()){
			return true;
		}
		return false;
	}
}