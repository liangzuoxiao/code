package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.ClueRemark;

import java.util.List;

/**
 * lzx
 * 2020/3/10
 */
public interface ClueRemarkDao {

    List<ClueRemark> selectClueRemarkForDetaByClueId(String clueId);

    List<ClueRemark> selectClueRemarkByClueId(String clueId);

    int deleteClueRemarkByClueId(String clueId);
}
