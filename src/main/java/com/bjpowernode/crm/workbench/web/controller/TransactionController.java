package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.DicService;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.dao.TransactionRemarkDao;
import com.bjpowernode.crm.workbench.domain.*;
import com.bjpowernode.crm.workbench.service.CustomerService;
import com.bjpowernode.crm.workbench.service.TransactionHistoryService;
import com.bjpowernode.crm.workbench.service.TransactionRemarkService;
import com.bjpowernode.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * lzx
 * 2020/3/13
 */
@Controller
public class TransactionController {
    @Autowired
    private UserService userService;

    @Autowired
    private DicService dicService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @Autowired
    private TransactionRemarkService transactionRemarkService;

    @RequestMapping("/workbench/transaction/toIndex.do")
    public String toIndex(){
        return "/workbench/transaction/index";
    }

    @RequestMapping("/workbench/transaction/toCreateTransaction.do")
    public String toCreateTransaction(Model model){
        //调用service层方法 查询数据
        List<User> userList= userService.getUserList();
        List<DicValue> stageList=dicService.queryDivValueByTyPeCode("stage");
        List<DicValue> tranTypeList=dicService.queryDivValueByTyPeCode("transactionType");
        List<DicValue> sourceList=dicService.queryDivValueByTyPeCode("source");
        //把数据保存到model中
        model.addAttribute("userList",userList);
        model.addAttribute("stageList",stageList);
        model.addAttribute("tranTypeList",tranTypeList);
        model.addAttribute("sourceList",sourceList);

        return "/workbench/transaction/save";
    }

    @RequestMapping("/workbench/transaction/getPossiBilityByStage.do")
    public @ResponseBody Object getPossiBilityByStage(String stageValue){
        ResourceBundle bundle = ResourceBundle.getBundle("properties/possibility");
        String value=bundle.getString(stageValue);

        return value;
    }

    @RequestMapping("/workbench/transaction/queryCustomerByName.do")
    public @ResponseBody Object queryCustomerByName(String name){
        //模糊查询自动补全要返回List或者数组
      List<Customer> customerList = customerService.queryCustomerByName(name);

        return customerList;
    }

    @RequestMapping("/workbench/transaction/saveCreateTransaction.do")
    public @ResponseBody Object saveCreateTransaction(Transaction transaction, HttpSession session){



        transaction.setId(UUIDUtil.getUUID());
        transaction.setCreateBy(((User)session.getAttribute("user")).getId());
        transaction.setCreateTime(DateTimeUtil.getSysTime());

        Map<String,Object> map = new HashMap<>();

        try {
            int ret=transactionService.saveCreateTransaction(transaction);
            if(transaction.getCustomerId()==null || transaction.getCustomerId().length()==0){
                if(ret>2){
                    map.put("code", "0");
                }else {
                    map.put("code", "1");
                }
            }else {
                if(ret>1){
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

    @RequestMapping("/workbench/transaction/detail.do")
    public String detail(String id,Model model){

        Transaction transaction=transactionService.queryTransactionForDetaById(id);
        List<TransactionRemark> transactionRemarkList = transactionRemarkService.queryTransactionRemarkByTranId(id);
        List<TransactionHistory> transactionHistoryList = transactionHistoryService.queryTransactionHistoryByTranId(id);

        //获取可能性
        ResourceBundle bundle = ResourceBundle.getBundle("properties/possibility");
        String value=bundle.getString(transaction.getStage());
        transaction.setPossibility(value);

        model.addAttribute("transaction",transaction);
        model.addAttribute("transactionRemarkList",transactionRemarkList);
        model.addAttribute("transactionHistoryList",transactionHistoryList);

        //查询交易所有阶段
        List<DicValue> stageList = dicService.queryDivValueByTyPeCode("stage");
        model.addAttribute("stageList",stageList);

        //求交易失败之前最后一个非失败阶段的orderNo 还没有做改变吗 交易阶段的改变 还没写了吧 是啊 哪就等写了之后再看 是因为你的最后选择的是可能性是0 所以它给你直接显示的是最后三个  等逻辑写完了 再看看 好的 恩恩 那你还有别的问题吗 没了 谢谢老师 客气了 那我就先下了 ok
//        TransactionHistory transactionHistory=null;
//        for (int i = transactionHistoryList.size()-1; i >=0 ; i--) {
//            transactionHistory=transactionHistoryList.get(i);
//            if(Integer.parseInt(transactionHistory.getOrderNo())<Integer.parseInt(stageList.get(stageList.size()-3).getOrderNo())){
//                model.addAttribute("theOrderNo",transactionHistory.getOrderNo());
//                break;
//            }
//        }
        TransactionHistory transactionHistory=null;
        for(int i=transactionHistoryList.size()-1;i>=0;i--){
            transactionHistory=transactionHistoryList.get(i);
            if(Integer.parseInt(transactionHistory.getOrderNo())<Integer.parseInt(stageList.get(stageList.size()-3).getOrderNo())){
                model.addAttribute("theOrderNo",transactionHistory.getOrderNo());
                break;
            }
        }

        return "/workbench/transaction/detail";
    }

    @RequestMapping("/workbench/transaction/saveEditTransactionStage.do")
    public @ResponseBody Object saveEditTransactionStage(String tranId,String stageId,String stageValue,HttpSession session){
        User user=(User) session.getAttribute("user");
        //封装参数
        Map<String,Object> map= new HashMap<>();
        Transaction transaction = transactionService.queryTransactionById(tranId);
        transaction.setStage(stageId);
        transaction.setEditBy(user.getId());
        transaction.setEditTime(DateTimeUtil.getSysTime());
        map.put("transaction", transaction);
        map.put("user", user);
        //调用service层方法，修改交易记录
        Map retMap=new HashMap();
        try {
            retMap=transactionService.saveEditTransactionStage(map);

            retMap.put("code","0");
            //获取可能性
            ResourceBundle bundle = ResourceBundle.getBundle("properties/possibility");
            String value=bundle.getString(stageValue);
            Transaction tran=(Transaction) retMap.get("transaction");
            tran.setPossibility(value);
            //设置阶段显示名称
            tran.setStage(stageValue);
            tran.setEditBy(user.getName());
            tran.setEditTime(transaction.getEditTime());

            TransactionHistory th=(TransactionHistory) retMap.get("th");
            th.setStage(stageValue);
            th.setCreateBy(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("code","1");
        }
        return retMap;
    }
    @RequestMapping("/workbench/transaction/queryCountOfTransactionGroupByStage.do")
    public@ResponseBody Object queryCountOfTransactionGroupByStage(){

        List<FunneIVO> funneIVOList = transactionService.queryCountOfTransactionGroupByStage();

        return funneIVOList;
    }


}
