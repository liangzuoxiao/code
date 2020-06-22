package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.dao.TransactionRemarkDao;
import com.bjpowernode.crm.workbench.domain.TransactionRemark;
import com.bjpowernode.crm.workbench.service.TransactionRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * lzx
 * 2020/3/14
 */
@Service
public class TransactionRemarkServiceImpl implements TransactionRemarkService {

    @Autowired
    private TransactionRemarkDao transactionRemarkDao;

    @Override
    public List<TransactionRemark> queryTransactionRemarkByTranId(String id) {
        return transactionRemarkDao.selectTransactionRemarkByTranId(id);
    }
}
