package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.TransactionRemark;

import java.util.List;

/**
 * lzx
 * 2020/3/14
 */
public interface TransactionRemarkService {

    List<TransactionRemark> queryTransactionRemarkByTranId(String id);
}
