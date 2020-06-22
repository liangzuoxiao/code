package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.dao.CustomerDao;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/13
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;


    @Override
    public List<Customer> queryCustomerByName(String name) {
        return customerDao.selectCustomerByName(name);
    }

    @Override
    public List<Customer> queryCustomerList() {
        return customerDao.getCustomerList();
    }

    @Override
    public int saveCustomer(Customer customer) {
        return customerDao.insertCustomer(customer);
    }

    @Override
    public PaginationVo<Customer> pageList(Map<String, Object> map) {
        //获得List
        List<Customer> dataList=customerDao.getCustomerListByCondition(map);
        //获得total
        int total=customerDao.getTotalByCondtion(map);
        //把数据放到vo
        PaginationVo<Customer> vo = new PaginationVo<>();
        vo.setDataList(dataList);
        vo.setTotal(total);
        return vo;
    }

    @Override
    public Customer queryCustomerById(String id) {
        return customerDao.selectCustomerById(id);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public int deleteCustomerByIds(String[] ids) {
        return customerDao.deleteCustomerByIds(ids);
    }
}
