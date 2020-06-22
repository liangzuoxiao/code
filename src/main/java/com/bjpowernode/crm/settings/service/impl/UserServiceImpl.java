package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.Const;
import com.bjpowernode.crm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/2/14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserList() {

        List<User> uList=userDao.getUserList();
        return uList;
    }

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        //1.验证账号密码是否正确
        Map<String,String> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        User user=userDao.login(map);

        if(user==null){
            //如果user==null，说明账号密码没有查询到
            throw new LoginException("账号密码错误");
        }

        //如果程序执行了该行，锁门上面的if语句块没有执行，说明user不为null，账号密码正确

        /*
            接下来从以上所得到的user对象中取得其他的验证项，继续验证

         */
        //验证失效时间
        String expireTime = user.getExpireTime();
        if(expireTime.compareTo(DateTimeUtil.getSysTime())<0){

            throw new LoginException("账号已失效");
        }

        //验证锁定状态
        String lockState = user.getLockState();
        if(lockState!=null){

            if(Const.LOGIN_LOCKSTATE_CLOSE.equals(lockState)){

                throw new LoginException("账号已锁定");
            }
        }

        //验证ip地址
        String allowIps = user.getAllowIps();
        if(allowIps!=null){

            if(!allowIps.contains(ip)){
                throw  new LoginException("ip地址受限");
            }
        }

        /*
            如果程序能够顺利的做到该行
            说明以上4个if语句块，都没有进入
            说明所有的验证都成功
            说明登陆成功

         */




        //返回user对象
        return user;
    }
}
