package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.dao.*;
import com.bjpowernode.crm.workbench.domain.*;
import com.bjpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/3/10
 */
@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueDao clueDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ContactsDao contactsDao;

    @Autowired
    private ClueRemarkDao clueRemarkDao;

    @Autowired
    private CustomerRemarkDao customerRemarkDao;

    @Autowired
    private ContactsRemarkDao contactsRemarkDao;

    @Autowired
    private ClueActivityRelationDao clueActivityRelationDao;

    @Autowired
    private ContactsActivityRelationDao contactsActivityRelationDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TransactionRemarkDao transactionRemarkDao;

    @Override
    public int saveCreateClue(Clue clue) {
        return clueDao.insertClue(clue);
    }

    @Override
    public Clue queryClueForDetaById(String id) {
        return clueDao.selectClueForDetailById(id);
    }

    @Override
    public void saveConvertClue(Map<String, Object> map) {
        String clueId=(String) map.get("clueId");
        User user=(User)map.get("user");
        String isCreateTransaction=(String) map.get("isCreateTransaction");

        //根据clueId查询线索信息
        Clue clue= clueDao.selectClueById(clueId);

        //把线索中有关公司的信息转换到客户表中
        Customer customer= new Customer();
        customer.setAddress(clue.getAddress());
        customer.setContactSummary(clue.getContactSummary());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateTimeUtil.getSysTime());
        customer.setDescription(clue.getDescription());
        customer.setId(UUIDUtil.getUUID());
        customer.setName(clue.getCompany());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setOwner(clue.getOwner());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
        customerDao.insertCustomer(customer);

        //把线索中有关个人的信息转换到联系人表中
        Contacts contacts = new Contacts();
        contacts.setAddress(clue.getAddress());
        contacts.setAppellation(clue.getAppellation());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateTimeUtil.getSysTime());
        contacts.setCustomerId(customer.getId());
        contacts.setDescription(clue.getDescription());
        contacts.setEmail(clue.getEmail());
        contacts.setFullname(clue.getFullname());
        contacts.setId(UUIDUtil.getUUID());
        contacts.setJob(clue.getJob());
        contacts.setMphone(clue.getMphone());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setOwner(clue.getOwner());
        contacts.setSource(clue.getSource());
        contactsDao.insertContacts(contacts);

        //根据clueId查询所有备注
        List<ClueRemark> clueRemarkList = clueRemarkDao.selectClueRemarkByClueId(clueId);

        if(clueRemarkList!=null||clueRemarkList.size()>0) {
            //把该线索下所有的备注转换客户备注表一份
            List<CustomerRemark> customerRemarkList = new ArrayList<>();
            List<ContactsRemark> contactsRemarkList = new ArrayList<>();
            CustomerRemark customerRemark = null;
            ContactsRemark contactsRemark = null;
            for (ClueRemark clueRemark : clueRemarkList) {
                customerRemark= new CustomerRemark();
                customerRemark.setCreateBy(clueRemark.getCreateBy());
                customerRemark.setCreateTime(clueRemark.getCreateTime());
                customerRemark.setCustomerId(customer.getId());
                customerRemark.setEditBy(clueRemark.getEditBy());
                customerRemark.setEditTime(clueRemark.getEditTime());
                customerRemark.setEditFlag(clueRemark.getEditFlag());
                customerRemark.setId(UUIDUtil.getUUID());
                customerRemark.setNoteContent(clueRemark.getNoteContent());
                customerRemarkList.add(customerRemark);

                contactsRemark=new ContactsRemark();
                contactsRemark.setCreateBy(clueRemark.getCreateBy());
                contactsRemark.setCreateTime(clueRemark.getCreateTime());
                contactsRemark.setContactsId(contacts.getId());
                contactsRemark.setEditBy(clueRemark.getEditBy());
                contactsRemark.setEditTime(clueRemark.getEditTime());
                contactsRemark.setEditFlag(clueRemark.getEditFlag());
                contactsRemark.setId(UUIDUtil.getUUID());
                contactsRemark.setNoteContent(clueRemark.getNoteContent());
                contactsRemarkList.add(contactsRemark);

            }
            //调用dao层方法，保存客户备注list
            customerRemarkDao.insertCustomerRemarkByList(customerRemarkList);
            contactsRemarkDao.insertContactsRemarkByList(contactsRemarkList);
        }

        //根据clueId查询线索和市场活动的关联线索
        List<ClueActivityRelation> clueActivityRelationList=clueActivityRelationDao.selectClueActivityRelationByClueId(clueId);

        //把线索和市场活动的关联关系转换到联系人和市场活动的关联关系中
        if(clueActivityRelationList!=null || clueActivityRelationList.size()>0){
            List<ContactsActivityRelation> contactsActivityRelationList= new ArrayList<>();
            ContactsActivityRelation contactsActivityRelation= null;
            for (ClueActivityRelation clueActivityRelation : clueActivityRelationList) {
                contactsActivityRelation= new ContactsActivityRelation();
                contactsActivityRelation.setId(UUIDUtil.getUUID());
                contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
                contactsActivityRelation.setContactsId(contacts.getId());
                contactsActivityRelationList.add(contactsActivityRelation);
            }
            //调用dao层方法，保存数据
            contactsActivityRelationDao.insertContactsActivityRelationByList(contactsActivityRelationList);
        }

        //如果需要创建交易
        if("true".equals(isCreateTransaction)){
            //添加交易记录
            Transaction transaction=new Transaction();
            transaction.setActivityId((String)map.get("activityId"));
            transaction.setContactsId(contacts.getId());
            transaction.setCreateBy(user.getId());
            transaction.setCreateTime(DateTimeUtil.getSysTime());
            transaction.setCustomerId(customer.getId());
            transaction.setExpectedDate((String)map.get("expectedClosingDate"));
            transaction.setId(UUIDUtil.getUUID());
            transaction.setMoney((String)map.get("amountOfMoney"));
            transaction.setName((String)map.get("transactionName"));
            transaction.setOwner(user.getId());
            transaction.setStage((String)map.get("stage"));
            //调用dao层方法，保存数据
            transactionDao.insertTransaction(transaction);

            //把线索备注转换到交易备注表中一份
            List<TransactionRemark> transactionRemarkList=new ArrayList<>();
            TransactionRemark transactionRemark=null;
            for(ClueRemark clueRemark:clueRemarkList){
                transactionRemark=new TransactionRemark();
                transactionRemark.setCreateBy(clueRemark.getCreateBy());
                transactionRemark.setCreateTime(clueRemark.getCreateTime());
                transactionRemark.setEditBy(clueRemark.getEditBy());
                transactionRemark.setEditTime(clueRemark.getEditTime());
                transactionRemark.setEditFlag(clueRemark.getEditFlag());
                transactionRemark.setId(UUIDUtil.getUUID());
                transactionRemark.setNoteContent(clueRemark.getNoteContent());
                transactionRemark.setTranId(transaction.getId());
                transactionRemarkList.add(transactionRemark);
            }
            //调用dao层方法，保存数据
            transactionRemarkDao.insertTransactionRemarkByList(transactionRemarkList);
        }

        //根据clueId删除该线索下的备注
        clueRemarkDao.deleteClueRemarkByClueId(user.getId());

        //根据clueId删除线索和市场活动的关联关系
        clueActivityRelationDao.deleteClueActivityRelationByClueId(user.getId());

        //根据id删除线索
        clueDao.deleteClueById(user.getId());
    }

    @Override
    public PaginationVo<Clue> pageList(Map<String, Object> paramMap) {
        //取得dataList
        List<Clue> dataList=clueDao.getClueListByCondition(paramMap);
        //取得total
        int total =clueDao.getTotalByCondition(paramMap);

        //创建一个VO类型的对象
        PaginationVo<Clue> vo = new PaginationVo<>();
        vo.setDataList(dataList);
        vo.setTotal(total);

        return vo;
    }

    @Override
    public Clue getToUpdateClueById(String id) {
        Clue clue=clueDao.selectToUpdateClueById(id);
        return clue;
    }

    @Override
    public int updateClueById(Clue clue) {
        return clueDao.updateClueById(clue);
    }

    @Override
    public int deleteClueByIds(String[] ids) {
        return clueDao.deleteClueByIds(ids);
    }
}
