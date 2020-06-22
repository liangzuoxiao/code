package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/2/23
 */
public interface ActivityService {

    void saveActivity(Activity a);

    Map<String, Object> pegeList(Map<String, Object> paramMap);

    void deletActivity(String[] ids);

    Map<String, Object> getUserListAndActivityById(String id);

    void updateActivity(Activity a);

    List<Activity> queryAllActivity();

    List<Activity> exportZxActivity(String[] ids);

    int saveImportActivity(List<Activity> activityList);

    Activity queryActivityForDetailById(String id);

    List<Activity> queryActivityForClueByClueId(String clueId);

    List<Activity> queryActivityByNameClueId(Map<String,Object> map);
}
