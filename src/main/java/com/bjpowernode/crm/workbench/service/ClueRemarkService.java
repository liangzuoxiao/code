package com.bjpowernode.crm.workbench.service;


import com.bjpowernode.crm.workbench.domain.ClueRemark;

;import java.util.List;

/**
 * lzx
 * 2020/3/10
 */
public interface ClueRemarkService {


    List<ClueRemark> queryClueRemarkByClueId(String clueId);
}
