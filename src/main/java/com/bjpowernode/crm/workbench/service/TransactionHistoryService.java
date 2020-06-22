package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.TransactionHistory;

import java.util.List;

/**
 * lzx
 * 2020/3/14
 */
public interface TransactionHistoryService {
    List<TransactionHistory> queryTransactionHistoryByTranId(String id);
}
