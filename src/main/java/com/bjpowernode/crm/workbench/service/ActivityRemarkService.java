package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * lzx
 * 2020/3/6
 */
public interface ActivityRemarkService {
    List<ActivityRemark> queryActivityRemarkByActivityId(String id);

    int saveCreateActivityRemark(ActivityRemark remark);

    int saveEditActivityRemark(ActivityRemark remark);

    int deltetActivityRemarkById(String id);
}