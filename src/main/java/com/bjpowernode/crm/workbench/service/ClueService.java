package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Clue;

import java.util.Map;

/**
 * lzx
 * 2020/3/10
 */
public interface ClueService {
    int saveCreateClue(Clue clue);

    Clue queryClueForDetaById(String id);

    void saveConvertClue(Map<String, Object> map);

    PaginationVo<Clue> pageList(Map<String, Object> paramMap);

    Clue getToUpdateClueById(String id);

    int updateClueById(Clue clue);

    int deleteClueByIds(String[] ids);
}
