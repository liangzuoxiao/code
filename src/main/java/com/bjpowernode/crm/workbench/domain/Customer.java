package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/12
 */
public class Customer {

    private String id;  //主键
    private String owner;   //所有者
    private String name;
    private String website; //网站
    private String phone;   //手机
    private String createBy;   //创建人
    private String createTime;  //创建时间
    private String editBy;   //修改人
    private String editTime;    //修改时间
    private String contactSummary;  //联系纪要
    private String nextContactTime; //下次联系时间
    private String description; //描述
    private String address; //详细地址

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public Customer setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public Customer setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Customer setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Customer setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public Customer setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getEditTime() {
        return editTime;
    }

    public Customer setEditTime(String editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public Customer setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
        return this;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public Customer setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Customer setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }


}
