package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.DicService;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;
import com.bjpowernode.crm.workbench.domain.ClueRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.ClueActivityRealtionService;
import com.bjpowernode.crm.workbench.service.ClueRemarkService;
import com.bjpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/9
 */
@Controller
@RequestMapping("/workbench/clue")
public class ClueController {
    @Autowired
    private UserService userService;

    @Autowired
    private DicService dicService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueActivityRealtionService clueActivityRealtionService;

    @RequestMapping("/index.do")
    public String index(Model model){
        //查询用户、称号、线索状态、线索来源
        List<User> userList=userService.getUserList();
        List<DicValue> appellationList=dicService.queryDivValueByTyPeCode("appellation");
        List<DicValue> clueStateList=dicService.queryDivValueByTyPeCode("clueState");
        List<DicValue> sourceList=dicService.queryDivValueByTyPeCode("source");

        //把数据保存在model中
        model.addAttribute("userList",userList);
        model.addAttribute("appellationList",appellationList);
        model.addAttribute("clueStateList",clueStateList);
        model.addAttribute("sourceList",sourceList);

        return "/workbench/clue/index";
    }
    @RequestMapping("/saveCreateClue.do")
    public @ResponseBody Object saveCreateClue(Clue clue, HttpSession session){
        User user=(User) session.getAttribute("user");

        clue.setId(UUIDUtil.getUUID());
        clue.setCreateBy(user.getId());
        clue.setCreateTime(DateTimeUtil.getSysTime());

        Map<String,Object> map = new HashMap<>();
        try {
            int ret=clueService.saveCreateClue(clue);
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

    @RequestMapping("/pageList.do")
    @ResponseBody
    public PaginationVo<Clue> cluePageList(String pageNoStr,String pageSizeStr,Clue clue){
        //将分页相关的参数格式化为int类型
        int pageNo=Integer.valueOf(pageNoStr);
        int pageSize=Integer.valueOf(pageSizeStr);

        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;

        Map<String,Object> paramMap= new HashMap<>();
        paramMap.put("skipCount", skipCount);
        paramMap.put("pageSize", pageSize);
        paramMap.put("clue", clue);

        PaginationVo<Clue> vo=clueService.pageList(paramMap);

        return vo;
    }

    @RequestMapping("/toUpdateClueById.do")
    public @ResponseBody Object toUpdateClueById(String id){
        Clue clue=clueService.getToUpdateClueById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("clue", clue);
        return map;
    }


    @RequestMapping("/updateClue.do")
    public @ResponseBody Object updateClue(Clue clue,HttpSession session){
        User user=(User) session.getAttribute("user");
        clue.setEditBy(user.getId());
        clue.setEditTime(DateTimeUtil.getSysTime());

        Map<String,Object> map = new HashMap<>();
        try {
            int ret=clueService.updateClueById(clue);
            if(ret>0) {
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

    @RequestMapping("/deleteClue.do")
    public @ResponseBody Object deleteClue(String[] ids){

        Map<String,Object> map = new HashMap<>();
        try {
            int ret=clueService.deleteClueByIds(ids);
            if(ret>0) {
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

    @RequestMapping("/detailClue.do")
    public String detailClue(String id,Model model){
        //查询数据
        Clue clue=clueService.queryClueForDetaById(id);
        List<ClueRemark> remarkList=clueRemarkService.queryClueRemarkByClueId(id);
        List<Activity> activityList=activityService.queryActivityForClueByClueId(id);

        model.addAttribute("clue",clue);
        model.addAttribute("remarkList",remarkList);
        model.addAttribute("activityList",activityList);

        return "/workbench/clue/detail" ;
    }

    @RequestMapping("/queryActivityByNameClueId.do")
    public @ResponseBody Object queryActivityByNameClueId(String activityName,String clueId){

        Map<String,Object> map = new HashMap<>();
        map.put("name", activityName);
        map.put("clueId", clueId);

        List<Activity> activityList = activityService.queryActivityByNameClueId(map);

        return activityList;
    }

    @RequestMapping("/bundActivity.do")
    public @ResponseBody Object bundActivity(String[] activityId,String clueId){

        List<ClueActivityRelation> list=new ArrayList<>();

        ClueActivityRelation realtion = null;
        for (String aid : activityId) {
            realtion=new ClueActivityRelation();
            realtion.setId(UUIDUtil.getUUID());
            realtion.setClueId(clueId);
            realtion.setActivityId(aid);
            list.add(realtion);
        }
        Map<String,Object> map= new HashMap<>();
        try {
            int ret=clueActivityRealtionService.saveCreateClueActivityRealtionByList(list);
            if(ret>0){
                map.put("code", "0");
                List<Activity> activityList=activityService.exportZxActivity(activityId);
                map.put("activityList", activityList);
            }else {
                map.put("code", "1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }

        return map;
    }

    @RequestMapping("/unbundActivity.do")
    public @ResponseBody Object unbundActivity(String clueId,String activityId){

        Map<String,Object> map = new HashMap<>();
        map.put("clueId", clueId);
        map.put("activityId", activityId);

        Map<String,Object> retMap= new HashMap<>();
        try {
            int ret=clueActivityRealtionService.deleteClueActivityRealtionByClueIdAndActivityId(map);

            if(ret>0){
                retMap.put("code", "0");

            }else {
                retMap.put("code", "1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("code", "1");
        }
        return retMap;
    }

    @RequestMapping("/toConvert.do")
    public String toConvert(String id,Model model){

        Clue clue=clueService.queryClueForDetaById(id);
        List<DicValue> stageList=dicService.queryDivValueByTyPeCode("stage");

        model.addAttribute("clue",clue);
        model.addAttribute("stageList",stageList);

        return "/workbench/clue/convert";
    }

    @RequestMapping("/saveConvert.do")
    public @ResponseBody Object saveConvert(String clueId,String amountOfMoney,String transactionName,String expectedClosingDate,String stage,String activityId,String isCreateTransaction,HttpSession session){

        User user=(User) session.getAttribute("user");

        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("clueId", clueId);
        map.put("amountOfMoney", amountOfMoney);
        map.put("transactionName", transactionName);
        map.put("expectedClosingDate", expectedClosingDate);
        map.put("stage", stage);
        map.put("activityId", activityId);
        map.put("isCreateTransaction", isCreateTransaction);
        map.put("user", user);

        Map<String,Object> retMap=new HashMap<>();
        try {
            clueService.saveConvertClue(map);
            retMap.put("code", "0");
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("code", "1");
        }

        return retMap;
    }
}
