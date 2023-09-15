package com.tyza66.orderquerytool.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Author: tyza66
 * Date: 2023/9/15 15:31
 * Github: https://github.com/tyza66
 **/

@Data
@TableName("`order`")
public class Order {
    Integer id;
    String userId;
    String orderId;
    Timestamp orderTime;
    BigDecimal orderAmount;
    Integer isDelete;
}
