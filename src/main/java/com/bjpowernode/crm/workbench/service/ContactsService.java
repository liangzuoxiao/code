package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Contacts;

import java.util.List;

/**
 * lzx
 * 2020/3/20
 */
public interface ContactsService {
    List<Contacts> queryContactsListByCustomerId(String id);

    int deleteContactsById(String id);

    int saveCreateContacts(Contacts contacts);
}
