package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.TransactionRemark;

import java.util.List;

/**
 * lzx
 * 2020/3/13
 */
public interface TransactionRemarkDao {
    int insertTransactionRemarkByList(List<TransactionRemark> transactionRemarkList);

    List<TransactionRemark> selectTransactionRemarkByTranId(String id);
}
