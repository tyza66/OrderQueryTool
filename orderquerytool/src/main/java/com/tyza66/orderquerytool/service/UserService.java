package com.tyza66.orderquerytool.service;

/**
 * Author: tyza66
 * Date: 2023/9/15 13:53
 * Github: https://github.com/tyza66
 **/

public interface UserService {
    //用户登录检查
    Boolean checkUser(String userAccount, String password);
    //用户注册
    Integer registerUser(String userAccount, String userName,String password, String email);

    //检查用户账号是否已经存在
    Boolean checkUserAccount(String userAccount);

    //检查用户邮箱是否存在
    Boolean checkEmail(String email);
}
