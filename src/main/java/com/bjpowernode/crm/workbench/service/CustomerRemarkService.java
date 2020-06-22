package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.CustomerRemark;

import java.util.List;

/**
 * lzx
 * 2020/3/20
 */
public interface CustomerRemarkService {

    List<CustomerRemark> queryCustomerRemarkListByCustomerId(String id);

    int saveCustomerRemark(CustomerRemark customerRemark);
}
