package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/2/23
 */
public class CustomerRemark {

    private String id;  //主键
    private String noteContent; //备注信息
    private String createTime;  //创建时间
    private String createBy;    //创建人
    private String editTime;    //修改时间
    private String editBy;  //修改人
    private String editFlag;    //是否已经修改过的标记
    private String customerId;  //市场活动id 外键 ---- 关联 tbl_activity


    public String getId() {
        return id;
    }

    public CustomerRemark setId(String id) {
        this.id = id;
        return this;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public CustomerRemark setNoteContent(String noteContent) {
        this.noteContent = noteContent;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public CustomerRemark setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public CustomerRemark setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getEditTime() {
        return editTime;
    }

    public CustomerRemark setEditTime(String editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public CustomerRemark setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public CustomerRemark setEditFlag(String editFlag) {
        this.editFlag = editFlag;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public CustomerRemark setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }
}
