package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.dao.CustomerRemarkDao;
import com.bjpowernode.crm.workbench.domain.CustomerRemark;
import com.bjpowernode.crm.workbench.service.CustomerRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * lzx
 * 2020/3/20
 */
@Service
public class CustomerRemarkServiceImpl implements CustomerRemarkService {

    @Autowired
    private CustomerRemarkDao customerRemarkDao;
    @Override
    public List<CustomerRemark> queryCustomerRemarkListByCustomerId(String id) {
        return customerRemarkDao.seleteCustomerRemarkListByCustomerId(id);
    }

    @Override
    public int saveCustomerRemark(CustomerRemark customerRemark) {
        return customerRemarkDao.insertCustomerRemark(customerRemark);
    }
}
