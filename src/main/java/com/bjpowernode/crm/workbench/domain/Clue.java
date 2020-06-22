package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/9
 */
public class Clue {
    private String id;
    private String fullname; //名称
    private String appellation; //称呼
    private String owner; //所有者
    private String company; //公司
    private String job; //职位
    private String email;   //邮箱
    private String phone;   //公司座机
    private String website; //公司网站
    private String mphone;  //手机
    private String state;   //线索状态
    private String source;  //线索来源
    private String createBy;    //创建者
    private String createTime;  //创建时间
    private String editBy;  //修改人
    private String editTime;    //修改时间
    private String description;     //线索描述
    private String contactSummary; //联系纪要
    private String nextContactTime; //下次联系时间
    private String address; //详细地址

    public String getId() {
        return id;
    }

    public Clue setId(String id) {
        this.id = id;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public Clue setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getAppellation() {
        return appellation;
    }

    public Clue setAppellation(String appellation) {
        this.appellation = appellation;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public Clue setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Clue setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getJob() {
        return job;
    }

    public Clue setJob(String job) {
        this.job = job;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Clue setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Clue setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public Clue setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getMphone() {
        return mphone;
    }

    public Clue setMphone(String mphone) {
        this.mphone = mphone;
        return this;
    }

    public String getState() {
        return state;
    }

    public Clue setState(String state) {
        this.state = state;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Clue setSource(String source) {
        this.source = source;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Clue setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Clue setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public Clue setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getEditTime() {
        return editTime;
    }

    public Clue setEditTime(String editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Clue setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public Clue setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
        return this;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public Clue setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Clue setAddress(String address) {
        this.address = address;
        return this;
    }
}
