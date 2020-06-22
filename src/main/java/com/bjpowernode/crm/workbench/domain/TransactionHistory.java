package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/14
 */
public class TransactionHistory {
    private String id;
    private String stage;   //阶段
    private String money;   //金额
    private String expectedDate;    //预计成绩日期
    private String createTime;  //创建人
    private String createBy;    //创建时间
    private String tranId;      //外键Id

    //为了显示交易的图标，扩展的属性
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public TransactionHistory setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public String getId() {
        return id;
    }

    public TransactionHistory setId(String id) {
        this.id = id;
        return this;
    }

    public String getStage() {
        return stage;
    }

    public TransactionHistory setStage(String stage) {
        this.stage = stage;
        return this;
    }

    public String getMoney() {
        return money;
    }

    public TransactionHistory setMoney(String money) {
        this.money = money;
        return this;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public TransactionHistory setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public TransactionHistory setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public TransactionHistory setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getTranId() {
        return tranId;
    }

    public TransactionHistory setTranId(String tranId) {
        this.tranId = tranId;
        return this;
    }
}
