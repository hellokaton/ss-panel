package com.sp.ext;

import com.blade.kit.DateKit;
import com.blade.kit.EncrypKit;
import com.blade.kit.StringKit;
import com.sp.config.SpConst;
import com.sp.utils.Utils;

/**
 * Created by biezhi on 2017/2/19.
 */
public class Functions {

    public static String config(String key){
        if(StringKit.isNotBlank(key)){
            return SpConst.config.get("app." + key);
        }
        return "";
    }

    /**
     * 取某个区间的随机数
     * @param max
     * @return
     */
    public static int random(int max) {
        int radom = Integer.valueOf(StringKit.getRandomNumber(1, max));
        if(radom == 0){
            return 1;
        }
        return radom;
    }

    /**
     * 格式化日期
     * @param unixTime
     * @return
     */
    public static String fmtdate(Integer unixTime) {
        if(null != unixTime){
            return DateKit.formatDateByUnixTime(unixTime, "yyyy-MM-dd");
        }
        return "";
    }

    /**
     * 格式化日期
     * @param unixTime
     * @param patten
     * @return
     */
    public static String fmtdate(Integer unixTime, String patten) {
        if(null != unixTime && StringKit.isNotBlank(patten)){
            return DateKit.formatDateByUnixTime(unixTime, patten);
        }
        return "";
    }

    public static String flowAutoShow(int s){
        return Utils.flowAutoShow(s);
    }

    public static String gavatar(String email){
        String hash = EncrypKit.md5(email.trim().toLowerCase());
        return "https://secure.gravatar.com/avatar/"+hash;
    }

}
