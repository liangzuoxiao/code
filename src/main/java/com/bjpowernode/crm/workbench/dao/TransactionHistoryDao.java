package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.TransactionHistory;

import java.util.List;

/**
 * lzx
 * 2020/3/14
 */
public interface TransactionHistoryDao {

    int insertTransactionHistory(TransactionHistory transactionHistory);

    List<TransactionHistory> selectTransactionHistoryByTranId(String id);
}
