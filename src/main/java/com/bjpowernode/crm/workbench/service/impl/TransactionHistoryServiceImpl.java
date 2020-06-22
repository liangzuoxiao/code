package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.dao.TransactionHistoryDao;
import com.bjpowernode.crm.workbench.domain.TransactionHistory;
import com.bjpowernode.crm.workbench.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * lzx
 * 2020/3/14
 */
@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    private TransactionHistoryDao transactionHistoryDao;
    @Override
    public List<TransactionHistory> queryTransactionHistoryByTranId(String id) {
        return transactionHistoryDao.selectTransactionHistoryByTranId(id);
    }
}
