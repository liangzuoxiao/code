package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.FunneIVO;
import com.bjpowernode.crm.workbench.domain.Transaction;

import java.util.List;


/**
 * lzx
 * 2020/3/13
 */
public interface TransactionDao {
    int insertTransaction(Transaction transaction);

    Transaction selectTransactionForDetaById(String id);

    Transaction selectTransationById(String id);

    int updateTransactionStage(Transaction tran);

    List<FunneIVO> selectCountOfTransactionGroupByStage();

    List<Transaction> selectTransactionListByCustomerId(String id);

    int deleteTransactionById(String id);
}
