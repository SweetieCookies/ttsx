package com.zf.utils;

public class StringUtils {
    public static boolean isEmpty(String str){
        if(str.trim().length()==0||str==null){
            return true;
        }
        return  false;
    }
}
