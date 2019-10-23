package com.zf.utils;

import java.util.HashMap;

/**
 * 工具类：封装响应内容，会被转成json响应
 * R.ok()==>new R(0)==> {"code":0}
 * new R(1); {"code":1}
 * R.ok("登录成功")==> new R(0,"登录成功");// {"code":0,"msg":"登录成功"}
 */
public class R extends HashMap<String,Object> {

    private  boolean code;//true 操作成功  false 操作失败
    private String msg;
    public R(){}
    public R(boolean code){
        //this.code=code;
        this.put("code",code);
    }

    public R(boolean code, String msg){
        super.put("code",code);
        super.put("msg",msg);
    }

    public  static R ok(){
        return new R(true);
    }

    public  static R ok(String msg){
        return new R(true,msg);
    }

    public  static R error(){
        return new R(false);
    }
    public  static R error(String msg){
        return new R(false,msg);
    }

    public R put(String key,Object o){
        super.put(key,o);
        return this;
    }
}