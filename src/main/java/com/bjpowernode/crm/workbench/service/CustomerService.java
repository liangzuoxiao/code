package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/13
 */
public interface CustomerService {
    List<Customer> queryCustomerByName(String name);

    List<Customer> queryCustomerList();

    int saveCustomer(Customer customer);

    PaginationVo<Customer> pageList(Map<String, Object> map);

    Customer queryCustomerById(String id);

    int updateCustomer(Customer customer);

    int deleteCustomerByIds(String[] ids);
}
