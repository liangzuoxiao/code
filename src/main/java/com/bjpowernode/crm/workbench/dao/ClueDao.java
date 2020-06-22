package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/10
 */
public interface ClueDao {
    int insertClue(Clue clue);

    Clue selectClueForDetailById(String id);

    Clue selectClueById(String clueId);

    int deleteClueById(String id);

    List<Clue> getClueListByCondition(Map<String, Object> paramMap);

    int getTotalByCondition(Map<String, Object> paramMap);

    Clue selectToUpdateClueById(String id);

    int updateClueById(Clue clue);

    int deleteClueByIds(String[] ids);
}
