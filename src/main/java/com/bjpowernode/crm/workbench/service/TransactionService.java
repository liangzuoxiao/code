package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.FunneIVO;
import com.bjpowernode.crm.workbench.domain.Transaction;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/14
 */
public interface TransactionService {
    int saveCreateTransaction(Transaction transaction);

    Transaction queryTransactionForDetaById(String id);

    Transaction queryTransactionById(String id);

    Map<String,Object> saveEditTransactionStage(Map<String,Object> map);

    List<FunneIVO> queryCountOfTransactionGroupByStage();

    List<Transaction> queryTransactionListByCustomerId(String id);

    int deleteTransactionById(String id);
}
