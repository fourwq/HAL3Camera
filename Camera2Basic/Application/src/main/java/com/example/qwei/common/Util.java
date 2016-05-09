package com.example.qwei.common;

/**
 * Created by camera on 2016/5/6.
 */
public class Util {
    public static String getStringFromStringArray(String[] array){
        StringBuilder builder = new StringBuilder();
        for (String item:array
             ) {
            builder.append(item+"\n");
        }
        return  builder.toString();
    }
}
