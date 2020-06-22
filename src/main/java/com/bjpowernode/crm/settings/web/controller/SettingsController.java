package com.bjpowernode.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * lzx
 * 2020/2/18
 */
@Controller
public class SettingsController {


    @RequestMapping("/settings/toIndex.do")
    public String toIndex(){

        return "/settings/index";
    }
}
