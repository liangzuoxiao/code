package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.dao.CustomerDao;
import com.bjpowernode.crm.workbench.dao.TransactionDao;
import com.bjpowernode.crm.workbench.dao.TransactionHistoryDao;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.domain.FunneIVO;
import com.bjpowernode.crm.workbench.domain.Transaction;
import com.bjpowernode.crm.workbench.domain.TransactionHistory;
import com.bjpowernode.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/14
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TransactionHistoryDao transactionHistoryDao;

    @Autowired
    private CustomerDao customerDao;
    @Override
    public int saveCreateTransaction(Transaction transaction) {

        int ret=0;
        //如果客户不存在，则创建新客户
        if(transaction.getCustomerId()==null||transaction.getCustomerId().length()==0){
            Customer customer = new Customer();
            customer.setOwner(transaction.getOwner());
            customer.setNextContactTime(transaction.getNextContactTime());
            customer.setName(transaction.getName());
            customer.setId(UUIDUtil.getUUID());
            customer.setDescription(transaction.getDescription());
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setCreateBy(transaction.getCreateBy());
            customer.setContactSummary(transaction.getContactSummary());
            ret+=customerDao.insertCustomer(customer);


            transaction.setCustomerId(customer.getId());
        }

        //保存交易记录
        ret+= transactionDao.insertTransaction(transaction);
        //保存交易历史记录
        TransactionHistory transactionHistory= new TransactionHistory();
        transactionHistory.setCreateBy(transaction.getCreateBy());
        transactionHistory.setCreateTime(transaction.getCreateTime());
        transactionHistory.setExpectedDate(transaction.getExpectedDate());
        transactionHistory.setId(UUIDUtil.getUUID());
        transactionHistory.setMoney(transaction.getMoney());
        transactionHistory.setStage(transaction.getStage());
        transactionHistory.setTranId(transaction.getId());

        ret+=transactionHistoryDao.insertTransactionHistory(transactionHistory);

        return ret;
    }

    @Override
    public Transaction queryTransactionForDetaById(String id) {
        return transactionDao.selectTransactionForDetaById(id);
    }

    @Override
    public Transaction queryTransactionById(String id) {
        return transactionDao.selectTransationById(id);
    }

    @Override
    public Map<String, Object> saveEditTransactionStage(Map<String, Object> map) {
        //修改交易阶段
        Transaction transaction=(Transaction) map.get("transaction");

        transactionDao.updateTransactionStage(transaction);
        //添加历史记录
        TransactionHistory th= new TransactionHistory();
        th.setTranId(transaction.getId());
        th.setStage(transaction.getStage());
        th.setMoney(transaction.getMoney());
        th.setId(UUIDUtil.getUUID());
        th.setExpectedDate(transaction.getExpectedDate());
        th.setCreateTime(DateTimeUtil.getSysTime());
        th.setCreateBy(((User)map.get("user")).getId());
        transactionHistoryDao.insertTransactionHistory(th);
        map.put("th",th);




        return map;

    }

    @Override
    public List<FunneIVO> queryCountOfTransactionGroupByStage() {
        return transactionDao.selectCountOfTransactionGroupByStage();
    }

    @Override
    public List<Transaction> queryTransactionListByCustomerId(String id) {
        return transactionDao.selectTransactionListByCustomerId(id);
    }

    @Override
    public int deleteTransactionById(String id) {
        return transactionDao.deleteTransactionById(id);
    }


}
