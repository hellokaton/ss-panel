package com.sp.model;

/**
 * Created by biezhi on 2017/2/19.
 */
public class Result {

    private int ret;
    private int error_code;
    private String msg;

    public Result(int ret) {
        this.ret = ret;
    }

    public Result(int ret, int error_code, String msg) {
        this.ret = ret;
        this.error_code = error_code;
        this.msg = msg;
    }

    public Result(int ret, int error_code) {
        this.ret = ret;
        this.error_code = error_code;
    }

    public Result(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static Result ok(){
        return new Result(1);
    }

    public static Result ok(String msg){
        return new Result(1, msg);
    }

    public static Result fail(){
        return new Result(0);
    }

    public static Result fail(int error_code){
        return new Result(0, error_code);
    }

    public static Result fail(String msg){
        return new Result(0, msg);
    }

    public static Result fail(int error_code, String msg){
        return new Result(0, error_code, msg);
    }

}
