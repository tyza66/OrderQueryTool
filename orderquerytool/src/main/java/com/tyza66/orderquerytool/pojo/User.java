package com.tyza66.orderquerytool.pojo;

import lombok.Data;

/**
 * Author: tyza66
 * Date: 2023/9/15 13:51
 * Github: https://github.com/tyza66
 **/

@Data
public class User {
    Integer id;
    String userId;
    String userAccount;
    String userName;
    String password;
    String email;
}
