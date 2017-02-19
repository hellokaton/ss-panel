package com.sp.dto;

import com.blade.kit.DateKit;
import com.sp.ext.Functions;
import com.sp.model.User;

/**
 * Created by biezhi on 2017/2/19.
 */
public class LoginUser extends User {

    public LoginUser(User user) {
        setId(user.getId());
        setUser_name(user.getUser_name());
        setEmail(user.getEmail());
        setPass(user.getPass());
        setPasswd(user.getPasswd());
        setT(user.getT());
        setU(user.getU());
        setD(user.getD());
        setTransfer_enable(user.getTransfer_enable());
        setPort(user.getPort());
        setProtocol(user.getProtocol());
        setObfs(user.getObfs());
        setSwitched(user.getSwitched());
        setEnable(user.getEnable());
        setType(user.getType());
        setLast_get_gift_time(user.getLast_get_gift_time());
        setLast_check_in_time(user.getLast_check_in_time());
        setLast_rest_pass_time(user.getLast_rest_pass_time());
        setReg_date(user.getReg_date());
        setInvite_num(user.getInvite_num());
        setIs_admin(user.getIs_admin());
        setRef_by(user.getRef_by());
        setExpire_time(user.getExpire_time());
        setMethod(user.getMethod());
        setIs_email_verify(user.getIs_email_verify());
        setReg_ip(user.getReg_ip());
    }

    public double trafficUsagePercent(){
        int total = this.getU() + this.getD();
        int transferEnable = this.getTransfer_enable();
        if(transferEnable == 0){
            return 0;
        }
        int percent = total / transferEnable;
        percent = Math.round(percent) * 100;
        return percent;
    }

    public String enableTraffic(){
        return Functions.flowAutoShow(this.getTransfer_enable());
    }

    public String usedTraffic(){
        return Functions.flowAutoShow(this.getU() + this.getD());
    }

    public String unusedTraffic(){
        int total = this.getU() + this.getD();
        int transfer_enable = this.getTransfer_enable();
        return Functions.flowAutoShow(transfer_enable - total);
    }

    public String lastCheckInTime(){
        if(this.getLast_check_in_time() == 0){
            return "从未签到";
        }
        return DateKit.formatDateByUnixTime(this.getLast_check_in_time(), "yyyy-MM-dd HH:mm:ss");
    }

    public boolean isAbleToCheckin(){
        Integer ct = Integer.valueOf(Functions.config("checkinTime"));
        if(this.getLast_check_in_time() + ct < DateKit.getCurrentUnixTime()){
            return true;
        }
        return false;
    }

    public String lastSsTime(){
        if(this.getT() == 0){
            return "从未使用喵";
        }
        return DateKit.formatDateByUnixTime(getT(), "yyyy-MM-dd HH:mm:ss");
    }

}