package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/11
 */
public interface ClueActivityRealtionService {
    int saveCreateClueActivityRealtionByList(List<ClueActivityRelation> list);

    int deleteClueActivityRealtionByClueIdAndActivityId(Map<String, Object> map);
}
