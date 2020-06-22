package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/12
 */
public class Contacts {

    private String id;  //主键
    private String owner;   //所有者
    private String source;  //来源
    private String customerId;  //客户Id
    private String fullname;    //姓名
    private String appellation; //称呼
    private String email;   //邮箱
    private String mphone;  //手机
    private String job; //职位
    private String birth;   //生日
    private String createBy;    //创建人
    private String createTime;  //创建时间
    private String editBy;  //修改人
    private String editTime;    //修改时间
    private String description; //描述
    private String contactSummary;  //联系纪要
    private String nextContactTime; //下次联系时间
    private String address; //地址

    //为了在创建联系人时，客户不存在，创建客户，扩展的属性
    private String customerName;


    public String getCustomerName() {
        return customerName;
    }

    public Contacts setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getId() {
        return id;
    }

    public Contacts setId(String id) {
        this.id = id;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public Contacts setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Contacts setSource(String source) {
        this.source = source;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Contacts setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public Contacts setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getAppellation() {
        return appellation;
    }

    public Contacts setAppellation(String appellation) {
        this.appellation = appellation;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contacts setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMphone() {
        return mphone;
    }

    public Contacts setMphone(String mphone) {
        this.mphone = mphone;
        return this;
    }

    public String getJob() {
        return job;
    }

    public Contacts setJob(String job) {
        this.job = job;
        return this;
    }

    public String getBirth() {
        return birth;
    }

    public Contacts setBirth(String birth) {
        this.birth = birth;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Contacts setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Contacts setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public Contacts setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getEditTime() {
        return editTime;
    }

    public Contacts setEditTime(String editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Contacts setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public Contacts setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
        return this;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public Contacts setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Contacts setAddress(String address) {
        this.address = address;
        return this;
    }
}
