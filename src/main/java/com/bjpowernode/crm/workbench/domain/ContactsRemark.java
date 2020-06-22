package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/13
 */
public class ContactsRemark {
    private String id;  //主键
    private String noteContent; //备注信息
    private String createTime;  //创建时间
    private String createBy;    //创建人
    private String editTime;    //修改时间
    private String editBy;  //修改人
    private String editFlag;    //是否已经修改过的标记
    private String contactsId;  //市场活动id 外键 ---- 关联 tbl_activity

    public String getId() {
        return id;
    }

    public ContactsRemark setId(String id) {
        this.id = id;
        return this;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public ContactsRemark setNoteContent(String noteContent) {
        this.noteContent = noteContent;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ContactsRemark setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public ContactsRemark setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getEditTime() {
        return editTime;
    }

    public ContactsRemark setEditTime(String editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public ContactsRemark setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public ContactsRemark setEditFlag(String editFlag) {
        this.editFlag = editFlag;
        return this;
    }

    public String getContactsId() {
        return contactsId;
    }

    public ContactsRemark setContactsId(String contactsId) {
        this.contactsId = contactsId;
        return this;
    }
}
