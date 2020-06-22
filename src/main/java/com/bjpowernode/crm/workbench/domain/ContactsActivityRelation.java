package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/13
 */
public class ContactsActivityRelation {
    private String id;
    private String contactsId;
    private String activityId;

    public String getId() {
        return id;
    }

    public ContactsActivityRelation setId(String id) {
        this.id = id;
        return this;
    }

    public String getContactsId() {
        return contactsId;
    }

    public ContactsActivityRelation setContactsId(String contactsId) {
        this.contactsId = contactsId;
        return this;
    }

    public String getActivityId() {
        return activityId;
    }

    public ContactsActivityRelation setActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }
}
