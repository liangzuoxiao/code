package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;

import java.util.List;

/**
 * lzx
 * 2020/2/14
 */
public interface UserService {

    List<User> getUserList();

    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
