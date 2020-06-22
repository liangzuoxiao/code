package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.ContactsRemark;

import java.util.List;

/**
 * lzx
 * 2020/3/13
 */
public interface ContactsRemarkDao {
    int insertContactsRemarkByList(List<ContactsRemark> contactsRemarkList);
}
