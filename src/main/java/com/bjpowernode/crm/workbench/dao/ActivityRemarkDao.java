package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * lzx
 * 2020/2/23
 */
public interface ActivityRemarkDao {
    void deleteByActivityids(String[] ids);

    List<ActivityRemark> selectActivityRemarkByActivityId(String id);

    int insertActivityRemark(ActivityRemark remark);

    int updateActivityRemark(ActivityRemark remark);

    int deltetActivityRemarkById(String id);
}
