package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.DicService;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Contacts;
import com.bjpowernode.crm.workbench.domain.Customer;
import com.bjpowernode.crm.workbench.domain.CustomerRemark;
import com.bjpowernode.crm.workbench.domain.Transaction;
import com.bjpowernode.crm.workbench.service.ContactsService;
import com.bjpowernode.crm.workbench.service.CustomerRemarkService;
import com.bjpowernode.crm.workbench.service.CustomerService;
import com.bjpowernode.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * lzx
 * 2020/3/13
 */
@Controller
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private DicService dicService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRemarkService customerRemarkService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/workbench/customer/toIndex.do")
    public String toCustomerIndex(Model model){
        List<User> userList=userService.getUserList();
        List<Customer> customerList=customerService.queryCustomerList();
        model.addAttribute("userList",userList);
        model.addAttribute("customerList",customerList);

        return "/workbench/customer/index";
    }

    @RequestMapping("/workbench/customer/saveCustomer.do")
    public @ResponseBody Object saveCustomer(Customer customer, HttpSession session){
        User user=(User) session.getAttribute("user");
        customer.setId(UUIDUtil.getUUID());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateTimeUtil.getSysTime());

        Map<String,Object> map = new HashMap<>();
        try {
            int ret=customerService.saveCustomer(customer);
            if(ret>0){
                map.put("code", "0");
            }else {
                map.put("code", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }
        return map;
    }

    @RequestMapping("/workbench/customer/pageList.do")
    public @ResponseBody Object pageList(String pageNoStr,String pageSizeStr,Customer c){

        int pageNo=Integer.valueOf(pageNoStr);
        int pageSize=Integer.valueOf(pageSizeStr);

        int skipCount=(pageNo-1)*pageSize;
        //把参数放到map中
        Map<String,Object> map = new HashMap<>();
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize);
        map.put("c", c);

        PaginationVo<Customer> vo=customerService.pageList(map);

        return vo;
    }

    @RequestMapping("/workbench/customer/toUpdateCustomerById.do")
    public @ResponseBody Object toUpdateCustomerById(String id){

        Customer customer=customerService.queryCustomerById(id);

        Map<String,Object> map = new HashMap<>();
        map.put("customer", customer);
        return map;
    }

    @RequestMapping("/workbench/customer/updateCustomer.do")
    public @ResponseBody Object updateCustomer(Customer customer,HttpSession session){
        User user=(User) session.getAttribute("user");

        customer.setEditBy(user.getId());
        customer.setEditTime(DateTimeUtil.getSysTime());

        Map<String,Object> map = new HashMap<>();
        try {
            int ret=customerService.updateCustomer(customer);
            if (ret>0) {
                map.put("code", "0");
            } else {
                map.put("code", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }

        return map;
    }

    @RequestMapping("/workbench/customer/deleteCustomerByIds.do")
    public @ResponseBody Object deleteCustomerByIds(String[] ids){
        Map<String,Object> map= new HashMap<>();
        try {
            int ret=customerService.deleteCustomerByIds(ids);
            if(ret>0){
                map.put("code", "0");
            }else {
                map.put("code", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }

        return map;
    }

    @RequestMapping("/workbench/customer/toDetail.do")
    public String toCustomerDetail(String id,Model model){
        //获取客户的详细信息
        Customer customer=customerService.queryCustomerById(id);
        //获取客户的备注
        List<CustomerRemark> customerRemarkList=customerRemarkService.queryCustomerRemarkListByCustomerId(id);
        //获取客户的交易
        List<Transaction> transactionList=transactionService.queryTransactionListByCustomerId(id);
        //获取客户的联系人
        List<Contacts> contactsList=contactsService.queryContactsListByCustomerId(id);
        //查询用户，来源，称呼
        List<User> userList = userService.getUserList();
        List<DicValue> appellationList = dicService.queryDivValueByTyPeCode("appellation");
        List<DicValue> sourceList = dicService.queryDivValueByTyPeCode("source");

        //获取可能性
        ResourceBundle bundle = ResourceBundle.getBundle("properties/possibility");
        for (Transaction transaction : transactionList) {
            String value=bundle.getString(transaction.getStage());
            transaction.setPossibility(value);
        }

        //放入model
        model.addAttribute("customer",customer);
        model.addAttribute("customerRemarkList",customerRemarkList);
        model.addAttribute("transactionList",transactionList);
        model.addAttribute("contactsList",contactsList);
        model.addAttribute("userList",userList);
        model.addAttribute("appellationList",appellationList);
        model.addAttribute("sourceList",sourceList);

        return "/workbench/customer/detail";
    }

    @RequestMapping("workbench/customer/saveCustomerRemark.do")
    public @ResponseBody Object saveCustomerRemark(CustomerRemark remark,HttpSession session){
        //获取参数
        User user=(User)session.getAttribute("user");
        remark.setId(UUIDUtil.getUUID());
        remark.setCreateBy(user.getId());
        remark.setEditFlag("0");
        remark.setCreateTime(DateTimeUtil.getSysTime());

        Map<String,Object> map = new HashMap<>();
        //调service层
        try {
            int ret=customerRemarkService.saveCustomerRemark(remark);
            if(ret>0){
                map.put("remark", remark);
                map.put("code", "0");
            }else {
                map.put("code", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }

        return map;
    }

    @RequestMapping("/workbench/customer/deleteContactsById.do")
    public @ResponseBody Object deleteContactsById(String id){
        Map<String,Object> map = new HashMap<>();
        try {
            int ret=contactsService.deleteContactsById(id);
            if(ret>0){
                map.put("code", "0");
            }else {
                map.put("code", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }
        return map;
    }

    @RequestMapping("/workbench/customer/saveCreateContacts.do")
    public @ResponseBody Object saveCreateContacts(Contacts contacts,HttpSession session){
        User user=(User) session.getAttribute("user");
        contacts.setId(UUIDUtil.getUUID());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateTimeUtil.getSysTime());
        Map<String,Object> map= new HashMap<>();
        try {
            int ret=contactsService.saveCreateContacts(contacts);
            if(contacts.getCustomerId()==null||contacts.getCustomerId().length()==0){
                if(ret>1){
                   map.put("code", "0");
                }else {
                    map.put("code", "1");
                }
            }else {
                if(ret>0){
                    map.put("code", "0");
                }else {
                    map.put("code", "1");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }
        return map;
    }

    @RequestMapping("/workbench/customer/deleteTransactionById.do")
    public @ResponseBody Object deleteTransactionById(String id){
        Map<String,Object> map = new HashMap<>();
        try {
            int ret=transactionService.deleteTransactionById(id);
            if(ret>0){
                map.put("code", "0");
            }else {
                map.put("code", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
        }
        return map;
    }
}
