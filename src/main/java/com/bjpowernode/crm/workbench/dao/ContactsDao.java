package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Contacts;

import java.util.List;

/**
 * lzx
 * 2020/3/12
 */
public interface ContactsDao {
    int insertContacts(Contacts contacts);

    List<Contacts> selectContactsListByCustomerId(String id);

    int deleteContactsById(String id);
}
