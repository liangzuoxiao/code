package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/2/23
 */
public interface ActivityDao {
    void saveActivity(Activity a);

    List<Activity> getActivityListByCondition(Map<String, Object> paramMap);

    int getTotalByCondition(Map<String, Object> paramMap);

    void deleteActivity(String[] ids);

    Activity getActivityById(String id);

    void updateActivity(Activity a);

    List<Activity> getActivityList();

    List<Activity> selectActivityById(String[] id);

    int saveImportActivity(List<Activity> activityList);

    Activity selectActivityForDetailById(String id);

    List<Activity> seleteActivityForClueByClueId(String clueId);

    List<Activity> seleteActivityByNameClueId(Map<String,Object> map);
}
