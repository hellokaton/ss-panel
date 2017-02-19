package com.sp.utils;

import com.blade.kit.StringKit;
import com.blade.mvc.http.Request;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class Utils {

    private static Random random = new Random();

    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(Request request) {
        if (null == request) {
            return "0.0.0.0";
        }
        String ip = request.header("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.header("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.header("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.address();
        }
        return ip;
    }

    /**
     * 获取@的用户列表
     *
     * @param str
     * @return
     */
    public static Set<String> getAtUsers(String str) {
        Set<String> users = new HashSet<String>();
        if (StringKit.isNotBlank(str)) {
            Pattern pattern = Pattern.compile("\\@([a-zA-Z_0-9-]+)\\s");
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                users.add(matcher.group(1));
            }
        }

        return users;
    }

    public static boolean isEmail(String str) {
        if (StringKit.isBlank(str)) {
            return false;
        }
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断用户是否可以注册
     *
     * @param user_name
     * @return
     */
    public static boolean isSignup(String user_name) {
        if (StringKit.isNotBlank(user_name)) {
            user_name = user_name.toLowerCase();
            if (user_name.contains("admin") ||
                    user_name.contains("test") ||
                    user_name.contains("support")) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isLegalName(String str) {
        if (StringKit.isNotBlank(str)) {
            Pattern pattern = Pattern.compile("^[a-zA-Z_0-9]{4,16}$");
            if (!pattern.matcher(str).find()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static Integer getTodayTime() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
    }

    public static Integer getYesterdayTime() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, -24);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
    }

    public static Integer getTomorrowTime() {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.set(Calendar.HOUR_OF_DAY, 24);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);
        return Integer.valueOf(String.valueOf(tomorrow.getTimeInMillis()).substring(0, 10));
    }

    @SuppressWarnings("resource")
    public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            assert inputChannel != null;
            inputChannel.close();
            assert outputChannel != null;
            outputChannel.close();
        }
    }

    /**
     * 在字符串左侧填充一定数量的特殊字符
     *
     * @param o     可被 toString 的对象
     * @param width 字符数量
     * @param c     字符
     * @return 新字符串
     */
    public static String alignRight(Object o, int width, char c) {
        if (null == o)
            return null;
        String s = o.toString();
        int len = s.length();
        if (len >= width)
            return s;
        return new StringBuilder().append(dup(c, width - len)).append(s).toString();
    }

    /**
     * 在字符串右侧填充一定数量的特殊字符
     *
     * @param o     可被 toString 的对象
     * @param width 字符数量
     * @param c     字符
     * @return 新字符串
     */
    public static String alignLeft(Object o, int width, char c) {
        if (null == o)
            return null;
        String s = o.toString();
        int length = s.length();
        if (length >= width)
            return s;
        return new StringBuilder().append(s).append(dup(c, width - length)).toString();
    }

    /**
     * 复制字符
     *
     * @param c   字符
     * @param num 数量
     * @return 新字符串
     */
    public static String dup(char c, int num) {
        if (c == 0 || num < 1)
            return "";
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < num; i++)
            sb.append(c);
        return sb.toString();
    }

    public static String encrypt(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return new BASE64Encoder().encode(encryptedBytes);
    }

    public static String decrypt(String cipherText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherTextBytes = new BASE64Decoder().decodeBuffer(cipherText);
        byte[] decValue = cipher.doFinal(cipherTextBytes);
        return new String(decValue);
    }

    public static int toGB(int traffic) {
        return 1048576 * 1024 * traffic;
    }

    public static int toMB(int traffic) {
        return 1048576 * traffic;
    }

    public static int flowToGB(int traffic) {
        int gb = 1048576 * 1024;
        return traffic / gb;
    }

    public static int rand(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 根据流量值自动转换单位输出
     *
     * @param value
     * @return
     */
    public static String flowAutoShow(int value) {
        int kb = 1024;
        int mb = 1048576;
        int gb = 1073741824;
        if (Math.abs(value) > gb) {
            return Math.round(value / gb) + "GB";
        } else if (Math.abs(value) > mb) {
            return Math.round(value / mb) + "MB";
        } else if (Math.abs(value) > kb) {
            return Math.round(value / kb) + "KB";
        }
        return Math.round(value) + "";
    }
}
