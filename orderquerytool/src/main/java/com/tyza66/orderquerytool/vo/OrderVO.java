package com.tyza66.orderquerytool.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Author: tyza66
 * Date: 2023/9/15 15:33
 * Github: https://github.com/tyza66
 **/

@Data
public class OrderVO {
    String id;
    String userId;
    String userAccount;
    String userName;
    String email;
    String orderId;
    String orderTime;
    BigDecimal orderAmount;
}
