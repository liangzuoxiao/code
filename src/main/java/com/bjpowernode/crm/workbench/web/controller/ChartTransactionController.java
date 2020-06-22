package com.bjpowernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * lzx
 * 2020/3/16
 */
@Controller
public class ChartTransactionController {

    @RequestMapping("/workbench/chart/transaction/index.do")
    public String toChartTransaction(){
        return "/workbench/chart/transaction/index";
    }
}
