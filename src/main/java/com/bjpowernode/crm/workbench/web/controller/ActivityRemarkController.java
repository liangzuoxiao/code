package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * lzx
 * 2020/3/7
 */
@Controller
@RequestMapping("/workbench/activity")
public class ActivityRemarkController {
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/saveCreateRemark.do")
    public @ResponseBody Object saveCreateRemark(String noteContent, String activityId, HttpSession session){

        User user=(User) session.getAttribute("user");

        ActivityRemark remark=new ActivityRemark();
        remark.setActivityId(activityId);
        remark.setCreateBy(user.getId());
        remark.setCreateTime(DateTimeUtil.getSysTime());
        remark.setEditBy("0");
        remark.setId(UUIDUtil.getUUID());
        remark.setNoteContent(noteContent);

        //添加备注
        Map<String,Object> map = new HashMap<>();
        try {
            int ret=activityRemarkService.saveCreateActivityRemark(remark);

            if(ret>0){
                map.put("code", "0");
                map.put("remark", remark);
            }else {
                map.put("code", "1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }

        return map;
    }

    @RequestMapping("/saveEditActivityRemark.do")
    public @ResponseBody Object saveEditActivityRemark(String remarkId,String noteContent,HttpSession session){

        User user=(User) session.getAttribute("user");


        ActivityRemark remark = new ActivityRemark();
        remark.setNoteContent(noteContent);
        remark.setId(remarkId);
        remark.setEditBy(user.getId());
        remark.setEditFlag("1");
        remark.setEditTime(DateTimeUtil.getSysTime());

        Map<String,Object> map = new HashMap<>();
        try {

            int ret=activityRemarkService.saveEditActivityRemark(remark);
            if(ret>0){
                map.put("code", "0");
                map.put("remark", remark);
            }else {
                map.put("code", "1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }

        return map;
    }

    @RequestMapping("/deltetActivityRemarkById.do")
    public @ResponseBody Object deltetActivityRemarkById(String id){

        Map<String,Object> map= new HashMap<>();
        try {
            int ret=activityRemarkService.deltetActivityRemarkById(id);

            if(ret>0){
                map.put("code", "0");

            }else {
                map.put("code", "1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }
        return map;
    }

}
