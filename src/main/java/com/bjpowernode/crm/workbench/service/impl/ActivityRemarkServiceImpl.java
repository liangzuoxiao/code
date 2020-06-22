package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * lzx
 * 2020/3/6
 */
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivityRemarkDao activityRemarkDao;

    @Override
    public List<ActivityRemark> queryActivityRemarkByActivityId(String id) {
        return activityRemarkDao.selectActivityRemarkByActivityId(id);
    }

    @Override
    public int saveCreateActivityRemark(ActivityRemark remark) {
        return activityRemarkDao.insertActivityRemark(remark);
    }

    @Override
    public int saveEditActivityRemark(ActivityRemark remark) {

        return activityRemarkDao.updateActivityRemark(remark);
    }

    @Override
    public int deltetActivityRemarkById(String id) {
        return activityRemarkDao.deltetActivityRemarkById(id);
    }
}
