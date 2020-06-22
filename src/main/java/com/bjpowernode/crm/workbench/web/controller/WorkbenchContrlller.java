package com.bjpowernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * lzx
 * 2020/2/15
 */
@Controller
public class WorkbenchContrlller {

    @RequestMapping("/workbench/toIndex.do")
    public String toIndex(){
        return "/workbench/index";
    }

    @RequestMapping("/workbench/main/toIndex.do")
    public String toMainIndex(){
        return "/workbench/main/index";
    }

}
