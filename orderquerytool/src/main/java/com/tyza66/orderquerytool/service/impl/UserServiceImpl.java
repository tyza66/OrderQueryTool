package com.tyza66.orderquerytool.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.orderquerytool.mapper.UserMapper;
import com.tyza66.orderquerytool.pojo.User;
import com.tyza66.orderquerytool.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Author: tyza66
 * Date: 2023/9/15 13:57
 * Github: https://github.com/tyza66
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Override
    public Boolean checkUser(String userAccount, String password) {
        //加密密码
        password = SecureUtil.sha1(password);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account",userAccount);
        userQueryWrapper.eq("password",password);
        User user = baseMapper.selectOne(userQueryWrapper);
        if (user == null){
            return false;
        }else{
            return true;
        }
    }

    //注册用户 0是成功 1是账号已存在 2是邮箱已存在 3是插入失败
    @Override
    public Integer registerUser(String userAccount, String userName, String password, String email) {
        //加密密码
        password = SecureUtil.sha1(password);
        //检查用户名是否已经存在
        if (checkUserAccount(userAccount)){
            return 1;
        }
        //检查邮箱是否已经存在
        if (checkEmail(email)){
            return 2;
        }
        //开始创建对象进行插入
        User user = new User();
        user.setId(0);
        user.setUserId(UUID.randomUUID().toString());
        user.setUserAccount(userAccount);
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        int insert = baseMapper.insert(user);
        if (insert == 1){
            return 0;
        }else {
            return 3;
        }
    }

    @Override
    public Boolean checkUserAccount(String userAccount) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account",userAccount);
        User user = baseMapper.selectOne(userQueryWrapper);
        if (user == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean checkEmail(String email) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email",email);
        User user = baseMapper.selectOne(userQueryWrapper);
        if (user == null){
            return false;
        }else{
            return true;
        }
    }
}
