package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.ContactsActivityRelation;

import java.util.List;

/**
 * lzx
 * 2020/3/13
 */
public interface ContactsActivityRelationDao {

    int insertContactsActivityRelationByList(List<ContactsActivityRelation> contactsActivityRelationList);
}
