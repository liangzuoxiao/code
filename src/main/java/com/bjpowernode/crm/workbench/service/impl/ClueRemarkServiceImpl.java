package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.dao.ClueRemarkDao;
import com.bjpowernode.crm.workbench.domain.ClueRemark;
import com.bjpowernode.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * lzx
 * 2020/3/10
 */
@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    private ClueRemarkDao clueRemarkDao;
    @Override
    public List<ClueRemark> queryClueRemarkByClueId(String clueId) {
        return clueRemarkDao.selectClueRemarkForDetaByClueId(clueId);
    }
}
