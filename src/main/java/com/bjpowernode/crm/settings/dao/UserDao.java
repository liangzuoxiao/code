package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/2/14
 */
public interface UserDao {

    User login(Map<String,String> map);

    List<User> getUserList();
}
