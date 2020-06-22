package com.bjpowernode.crm.test;


import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * lzx
 * 2020/2/13
 */
public class Test02 {

    public static void main(String[] args) {


        //验证失效时间
        //失效时间

        String expireTime = "2020-01-30 23:50:55";
        //当前系统时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentSysTime = sdf.format(date);
        int count = expireTime.compareTo(currentSysTime);
        System.out.println(count);

    }

}
