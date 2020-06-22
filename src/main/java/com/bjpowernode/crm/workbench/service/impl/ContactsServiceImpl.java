package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.dao.ContactsDao;
import com.bjpowernode.crm.workbench.dao.CustomerDao;
import com.bjpowernode.crm.workbench.domain.Contacts;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin.util.UIUtil;

import java.util.List;

/**
 * lzx
 * 2020/3/20
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsDao contactsDao;

    @Autowired
    private CustomerDao customerDao;
    @Override
    public List<Contacts> queryContactsListByCustomerId(String id) {
        return contactsDao.selectContactsListByCustomerId(id);
    }

    @Override
    public int deleteContactsById(String id) {
        return contactsDao.deleteContactsById(id);
    }

    @Override
    public int saveCreateContacts(Contacts contacts) {
        int ret=0;

        //判断客户id为空就创建新客户
        if(contacts.getCustomerId()==null||contacts.getCustomerId().length()==0){
            Customer customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setCreateBy(contacts.getCreateBy());
            customer.setAddress(contacts.getAddress());
            customer.setContactSummary(contacts.getContactSummary());
            customer.setDescription(contacts.getDescription());
            customer.setName(contacts.getCustomerName());
            customer.setNextContactTime(contacts.getNextContactTime());
            customer.setOwner(contacts.getOwner());
            ret+=customerDao.insertCustomer(customer);
            //把id保存到联系人对象
            contacts.setCustomerId(customer.getId());
        }

        //创建联系人
        ret+=contactsDao.insertContacts(contacts);
        return ret;
    }
}
