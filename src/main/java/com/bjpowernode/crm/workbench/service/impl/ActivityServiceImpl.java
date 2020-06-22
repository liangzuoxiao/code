package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/2/23
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    @Autowired
    public ActivityRemarkDao activityRemarkDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void saveActivity(Activity a) {
        activityDao.saveActivity(a);
    }

    @Override
    public Map<String, Object> pegeList(Map<String, Object> paramMap) {
        //取得dataList
        List<Activity> dataList= activityDao.getActivityListByCondition(paramMap);
        //取得total
        int total=activityDao.getTotalByCondition(paramMap);
        //创建一个map对象，将dataList和
        Map<String,Object> map = new HashMap<>();
        map.put("dataList", dataList);
        map.put("total",total);
        return map;
    }

    @Override
    public void deletActivity(String[] ids) {

        activityRemarkDao.deleteByActivityids(ids);
        activityDao.deleteActivity(ids);
    }

    @Override
    public Map<String, Object> getUserListAndActivityById(String id) {
        //取得uList
        List<User> uList = userDao.getUserList();
        //取得a
        Activity a=activityDao.getActivityById(id);

        Map<String,Object> map = new HashMap<>();
        map.put("uList", uList);
        map.put("a", a);
        return map;
    }

    @Override
    public void updateActivity(Activity a) {

        activityDao.updateActivity(a);
    }

    @Override
    public List<Activity> queryAllActivity() {
        List<Activity> activityList=activityDao.getActivityList();
        return activityList;
    }

    @Override
    public List<Activity> exportZxActivity(String[] id) {
        List<Activity> activityList=activityDao.selectActivityById(id);
        return activityList;
    }

    @Override
    public int saveImportActivity(List<Activity> activityList) {

        return activityDao. saveImportActivity(activityList);
    }

    @Override
    public Activity queryActivityForDetailById(String id) {
       return activityDao.selectActivityForDetailById(id);
    }

    @Override
    public List<Activity> queryActivityForClueByClueId(String clueId) {
        return activityDao.seleteActivityForClueByClueId(clueId);
    }

    @Override
    public List<Activity> queryActivityByNameClueId(Map<String, Object> map) {
        return activityDao.seleteActivityByNameClueId(map);
    }
}
