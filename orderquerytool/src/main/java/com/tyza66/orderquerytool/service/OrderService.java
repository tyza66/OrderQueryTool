package com.tyza66.orderquerytool.service;

import com.tyza66.orderquerytool.vo.OrderVO;

import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/9/15 16:22
 * Github: https://github.com/tyza66
 **/

public interface OrderService {
    List<OrderVO> getOrders(String userId,String startTime,String endTime,String money1,String money2,Integer page,Integer size);

    Integer getOrdersSize(String userId,String startTime,String endTime,String money1,String money2);

    Integer deleteOrder(String id);
}
