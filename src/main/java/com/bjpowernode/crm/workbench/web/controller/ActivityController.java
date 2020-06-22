package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.exception.AjaxRequestException;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.HandleFlag;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import com.bjpowernode.crm.workbench.service.ActivityService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/2/23
 */

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityRemarkService activityRemarkService;


    @RequestMapping("/toActivityindex.do")
    public String toActivityindex(){

        return "/workbench/activity/index";
    }

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){

        List<User> uList=userService.getUserList();
        return uList;
    }

    @RequestMapping("/saveActivity.do")
    @ResponseBody
    public Map<String,Object> saveActivity(Activity a, HttpSession session)throws AjaxRequestException{

        String id= UUIDUtil.getUUID();
        //创建人，当前登录的用户
        String createBy= ((User)session.getAttribute("user")).getName();
        //创建时间，当前的系统时间
        String createTime= DateTimeUtil.getSysTime();

        a.setId(id);
        a.setCreateBy(createBy);
        a.setCreateTime(createTime);

        try {
            activityService.saveActivity(a);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AjaxRequestException();
        }

        return HandleFlag.successTrue();
    }

    @RequestMapping("/pegeList.do")
    @ResponseBody
    public Map<String,Object> pegeList(String pageNoStr,String pageSizeStr,String name,String owner,String startDate,String endDate){

        int pageNo =Integer.valueOf(pageNoStr);
        int pageSize=Integer.valueOf(pageSizeStr);

        int skipCount=(pageNo-1)*pageSize;

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("skipCount", skipCount);
        paramMap.put("pageSize",pageSize);
        paramMap.put("name",name);
        paramMap.put("owner",owner);
        paramMap.put("startDate",startDate);
        paramMap.put("endDate", endDate);
        Map<String,Object>  map=activityService.pegeList(paramMap);

        return map;

    }

    @RequestMapping("/deleteActivity.do")
    @ResponseBody
    public Map<String,Object> deleteActivity(String[] ids){

        activityService.deletActivity(ids);

        return HandleFlag.successTrue();
    }

    @RequestMapping("/toActivityUpdate.do")
    @ResponseBody
    public Map<String,Object> toActivityUpdate(String id){

        Map<String,Object> map=activityService.getUserListAndActivityById(id);
        return map;
    }

    @RequestMapping("/updateActivity.do")
    @ResponseBody
    public Map<String,Object> updateActivity(Activity a,HttpSession session){

        String editBy= ((User)session.getAttribute("user")).getName();
        //创建时间，当前的系统时间
        String editTime= DateTimeUtil.getSysTime();
        a.setEditBy(editBy);
        a.setEditTime(editTime);

        activityService.updateActivity(a);
        return HandleFlag.successTrue();
    }

    //批量导出
    @RequestMapping("/exporAllActivity.do")
    public void exporAllActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Activity> activityList=activityService.queryAllActivity();

        // 对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 页
        HSSFSheet sheet = workbook.createSheet("市场活动");
        //行
        HSSFRow row = sheet.createRow(0);
        //列
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("名称");
        cell=row.createCell(1);
        cell.setCellValue("所有者");
        cell=row.createCell(2);
        cell.setCellValue("开始日期");
        cell=row.createCell(3);
        cell.setCellValue("结束日期");

        Activity activity= null;
        for (int i = 0; i <activityList.size(); i++) {

            activity=activityList.get(i);


            row= sheet.createRow(i+1);

            cell=row.createCell(0);
            cell.setCellValue(activity.getName());
            cell=row.createCell(1);
            cell.setCellValue(activity.getOwner());
            cell=row.createCell(2);
            cell.setCellValue(activity.getStartDate());
            cell=row.createCell(3);
            cell.setCellValue(activity.getEndDate());
        }

        //根据HTTP协议的规定，浏览器每次向服务器发送请求，都会把浏览器信息以请求头的形式发送到服务器。
        String browser=request.getHeader("User-Agent");
        System.out.println("browser="+browser);

        //不同的浏览器接收响应头信息的所采用的编码格式不一样；IE采用application/x-www-form-urlencoded，火狐采用ISO8859-1
        String fn=URLEncoder.encode("市场活动", "utf-8");
        if(browser.toLowerCase().indexOf("firefox")>0){
            fn=new String("市场活动".getBytes("utf-8"),"ISO8859-1");
        }

        //浏览器接收到响应信息之后，默认会直接打开，可以设置响应头信息，激活文件下载窗口，而不是直接打开
        response.addHeader("Content-Disposition", "attachment;filename="+fn+".xls");

        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();

    }

    //选择导出
    @RequestMapping("/exportZxActivity.do")
    public void exportZxActivity(String[] id,HttpServletRequest request,HttpServletResponse response) throws IOException {

        List<Activity> activityList= activityService.exportZxActivity(id);
        // 对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 页
        HSSFSheet sheet = workbook.createSheet("市场活动");
        //行
        HSSFRow row = sheet.createRow(0);
        //列
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("名称");
        cell=row.createCell(1);
        cell.setCellValue("所有者");
        cell=row.createCell(2);
        cell.setCellValue("开始日期");
        cell=row.createCell(3);
        cell.setCellValue("结束日期");

        Activity activity= null;
        for (int i = 0; i <activityList.size(); i++) {

            activity=activityList.get(i);


            row= sheet.createRow(i+1);

            cell=row.createCell(0);
            cell.setCellValue(activity.getName());
            cell=row.createCell(1);
            cell.setCellValue(activity.getOwner());
            cell=row.createCell(2);
            cell.setCellValue(activity.getStartDate());
            cell=row.createCell(3);
            cell.setCellValue(activity.getEndDate());
        }

        //根据HTTP协议的规定，浏览器每次向服务器发送请求，都会把浏览器信息以请求头的形式发送到服务器。
        String browser=request.getHeader("User-Agent");
        System.out.println("browser="+browser);

        //不同的浏览器接收响应头信息的所采用的编码格式不一样；IE采用application/x-www-form-urlencoded，火狐采用ISO8859-1
        String fn=URLEncoder.encode("市场活动", "utf-8");
        if(browser.toLowerCase().indexOf("firefox")>0){
            fn=new String("市场活动".getBytes("utf-8"),"ISO8859-1");
        }

        //浏览器接收到响应信息之后，默认会直接打开，可以设置响应头信息，激活文件下载窗口，而不是直接打开
        response.addHeader("Content-Disposition", "attachment;filename="+fn+".xls");

        OutputStream os = response.getOutputStream();
        workbook.write(os);
        workbook.close();
    }

    //测试单击上传
    @RequestMapping("/toUpload.do")
    public String toUpload(){

        return "/workbench/activity/upload" ;
    }

    @RequestMapping("upload.do")
    public @ResponseBody Object upload(MultipartFile myFile,String username) throws IOException {

        System.out.println("username="+username);


        File file=new File("E:\\tools\\"+myFile.getOriginalFilename());
        myFile.transferTo(file);

        Map<String,Object> map= new HashMap<>();
        map.put("success", "true");

        return map;

    }

    @RequestMapping("/imporActivity.do")
    @ResponseBody
    public Map<String,Object> imporActivity(MultipartFile activityFile,HttpSession session){

        User user= ((User)session.getAttribute("user"));
        Map<String,Object> retMap= new HashMap<>();

        try {
            InputStream is = activityFile.getInputStream();

            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row= null;
            Activity activity= null;
            List<Activity> activityList= new ArrayList<>();
            for (int i = 1; i <=sheet.getLastRowNum() ; i++) {
                row=sheet.getRow(i);
                activity= new Activity();
                activity.setCreateBy(user.getId());
                activity.setCreateTime(DateTimeUtil.getSysTime());
                activity.setOwner(user.getId());
                activity.setId(UUIDUtil.getUUID());

                activity.setName(getValueFromCell(row.getCell(0)));
                activity.setStartDate(getValueFromCell(row.getCell(1)));
                activity.setEndDate((getValueFromCell(row.getCell(2))));
                activity.setCost(getValueFromCell(row.getCell(3)));
                activity.setDescription(getValueFromCell(row.getCell(4)));

                activityList.add(activity);
            }

            //保存数据
            int count=activityService.saveImportActivity(activityList);
            retMap.put("code", 1);
            retMap.put("count", count);
        } catch (IOException e) {
            e.printStackTrace();
            retMap.put("code", "0");
        }
        return retMap;
    }


    public String getValueFromCell(HSSFCell cell){

        int cellTye=cell.getCellType();

        String ret="";
        switch (cellTye){
            case HSSFCell.CELL_TYPE_BLANK:
                ret="";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                ret=cell.getBooleanCellValue()+"";
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                ret="";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                ret=cell.getCellFormula();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                ret=cell.getNumericCellValue()+"";
                break;
            case HSSFCell.CELL_TYPE_STRING:
                ret=cell.getStringCellValue();
        }
        return ret;

    }

    @RequestMapping("/detailActivity.do")
    public String detailActivity(String id, Model model){

        Activity activity=activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList=activityRemarkService.queryActivityRemarkByActivityId(id);

        model.addAttribute("activity",activity);
        model.addAttribute("remarkList",remarkList);

        return "/workbench/activity/detail";
    }
}



