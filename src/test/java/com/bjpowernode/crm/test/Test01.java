package com.bjpowernode.crm.test;

import java.util.UUID;

/**
 * lzx
 * 2020/2/13
 */
public class Test01 {

    public static void main(String[] args) {

        UUID uuid = UUID.randomUUID();
        String srt = uuid.toString();
        srt=srt.replaceAll("-", "");
        System.out.println(srt);
        System.out.println(srt.length());

    }
}
