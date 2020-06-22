package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.CustomerRemark;

import java.util.List;

/**
 * lzx
 * 2020/3/12
 */
public interface CustomerRemarkDao {

    int insertCustomerRemarkByList(List<CustomerRemark> customerRemarkList);


    List<CustomerRemark> seleteCustomerRemarkListByCustomerId(String id);

    int insertCustomerRemark(CustomerRemark customerRemark);
}
