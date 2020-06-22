package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/11
 */
public interface ClueActivityRelationDao {
    int insterClueActivityRelationByList(List<ClueActivityRelation> list);

    int deleteClueActivityRealtionByClueIdAndActivityId(Map<String, Object> map);

    List<ClueActivityRelation> selectClueActivityRelationByClueId(String clueId);

    int deleteClueActivityRelationByClueId(String clueId);
}
