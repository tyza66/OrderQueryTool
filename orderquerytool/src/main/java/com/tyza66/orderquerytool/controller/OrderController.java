package com.tyza66.orderquerytool.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyza66.orderquerytool.pojo.Order;
import com.tyza66.orderquerytool.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: tyza66
 * Date: 2023/9/15 16:22
 * Github: https://github.com/tyza66
 **/

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    //获得订单列表
    @GetMapping("/get")
    public JSON getOrders(String userId,String startTime,String endTime,String money1,String money2,Integer page,Integer size){
        JSONObject obj = JSONUtil.createObj();
        obj.set("msg","ok");
        obj.set("code",200);
        obj.set("data",orderService.getOrders(userId, startTime, endTime, money1, money2, page, size));
        obj.set("size",orderService.getOrdersSize(userId, startTime, endTime, money1, money2));
        return obj;
    }

    //按ID逻辑删除订单
    @GetMapping("/delete")
    public JSON deleteOrder(String id){
        JSONObject obj = JSONUtil.createObj();
        Integer integer = orderService.deleteOrder(id);
        if (integer==1){
            obj.set("msg","ok");
            obj.set("code",200);
        }else {
            obj.set("msg","error");
            obj.set("code",500);
        }
        return obj;
    }

    //修改订单信息
    @PostMapping ("/update")
    public JSON updateOrder(@RequestBody Order order){
        JSONObject obj = JSONUtil.createObj();
        boolean b = orderService.updateById(order);
        if (b){
            obj.set("msg","ok");
            obj.set("code",200);
        }else {
            obj.set("msg","error");
            obj.set("code",500);
        }
        return obj;
    }
}
