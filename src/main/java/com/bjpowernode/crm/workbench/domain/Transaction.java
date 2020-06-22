package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/13
 */
public class Transaction {

    private String id;
    private String owner;   //所有者
    private String money;   //金额
    private String name;    //名称
    private String expectedDate;    //预计成交日期
    private String customerId;  //客户ID
    private String stage;   //阶段
    private String type;    //类型
    private String source;  //来源
    private String activityId;  //市场活动
    private String contactsId;  //联系人
    private String createBy;    //创建人
    private String createTime;  //创建时间
    private String editBy;  //修改人
    private String editTime;    //修改时间
    private String description; //描述
    private String contactSummary;  //联系纪要
    private String nextContactTime; //下次联系时间

    //为了显示交易的可能性，扩展的属性
    private String possibility;
    //为了显示交易的图标，扩展的属性
    private String orderNo;
    //为了在创建交易时，客户不存在，创建客户，扩展的属性
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public Transaction setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Transaction setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getPossibility() {
        return possibility;
    }

    public Transaction setPossibility(String possibility) {
        this.possibility = possibility;
        return this;
    }

    public String getId() {
        return id;
    }

    public Transaction setId(String id) {
        this.id = id;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public Transaction setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getMoney() {
        return money;
    }

    public Transaction setMoney(String money) {
        this.money = money;
        return this;
    }

    public String getName() {
        return name;
    }

    public Transaction setName(String name) {
        this.name = name;
        return this;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public Transaction setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Transaction setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getStage() {
        return stage;
    }

    public Transaction setStage(String stage) {
        this.stage = stage;
        return this;
    }

    public String getType() {
        return type;
    }

    public Transaction setType(String type) {
        this.type = type;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Transaction setSource(String source) {
        this.source = source;
        return this;
    }

    public String getActivityId() {
        return activityId;
    }

    public Transaction setActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getContactsId() {
        return contactsId;
    }

    public Transaction setContactsId(String contactsId) {
        this.contactsId = contactsId;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Transaction setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Transaction setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public Transaction setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getEditTime() {
        return editTime;
    }

    public Transaction setEditTime(String editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Transaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public Transaction setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
        return this;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public Transaction setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
        return this;
    }
}
