package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.dao.ClueActivityRelationDao;
import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;
import com.bjpowernode.crm.workbench.service.ClueActivityRealtionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/11
 */
@Service
public class ClueActivityRealtionServiceImpl implements ClueActivityRealtionService {

    @Autowired
    private ClueActivityRelationDao clueActivityRelationDao;


    @Override
    public int saveCreateClueActivityRealtionByList(List<ClueActivityRelation> list) {
        return clueActivityRelationDao.insterClueActivityRelationByList(list) ;
    }

    @Override
    public int deleteClueActivityRealtionByClueIdAndActivityId(Map<String, Object> map) {
        return clueActivityRelationDao.deleteClueActivityRealtionByClueIdAndActivityId(map);
    }
}
