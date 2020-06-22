package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/12
 */
public interface CustomerDao {
    int insertCustomer(Customer customer);

    List<Customer> selectCustomerByName(String name);

    List<Customer> getCustomerList();

    List<Customer> getCustomerListByCondition(Map<String, Object> map);

    int getTotalByCondtion(Map<String, Object> map);

    Customer selectCustomerById(String id);

    int updateCustomer(Customer customer);

    int deleteCustomerByIds(String[] ids);
}
