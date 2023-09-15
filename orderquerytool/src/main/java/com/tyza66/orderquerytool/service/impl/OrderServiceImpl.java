package com.tyza66.orderquerytool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyza66.orderquerytool.mapper.OrderMapper;
import com.tyza66.orderquerytool.mapper.UserMapper;
import com.tyza66.orderquerytool.pojo.Order;
import com.tyza66.orderquerytool.pojo.User;
import com.tyza66.orderquerytool.service.OrderService;
import com.tyza66.orderquerytool.vo.OrderVO;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/9/15 16:22
 * Github: https://github.com/tyza66
 **/

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<OrderVO> getOrders(String userId, String startTime, String endTime, String money1,String money2, Integer page, Integer size) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        //逻辑删除字段不为1
        orderQueryWrapper.ne("is_delete",1);
        //最新时间的排在前面
        orderQueryWrapper.orderByDesc("order_time");
        //用户账号过滤
        if(userId!=null&&!userId.equals("")){
            orderQueryWrapper.eq("user_id",userId);
        }
        //大于最早时间过滤
        if(startTime!=null&&!startTime.equals("")){
            orderQueryWrapper.ge("order_time",startTime);
        }
        //小于最晚时间过滤
        if(endTime!=null&&!endTime.equals("")){
            orderQueryWrapper.le("order_time",endTime);
        }
        //大于最小金额money1过滤
        if(money1!=null&&!money1.equals("")){
            orderQueryWrapper.ge("order_amount",money1);
        }
        //小于最大金额money2过滤
        if(money2!=null&&!money2.equals("")){
            orderQueryWrapper.le("order_amount",money2);
        }
        Page<Order> orderPage = baseMapper.selectPage(new Page<>(page, size), orderQueryWrapper);
        List<Order> records = orderPage.getRecords();
        List<OrderVO> orderVOS = new ArrayList<>();
        for (Order record : records) {
            OrderVO orderVO = new OrderVO();
            orderVO.setId(record.getId().toString());
            orderVO.setUserId(record.getUserId());
            orderVO.setOrderId(record.getOrderId());
            orderVO.setOrderAmount(record.getOrderAmount());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
            orderVO.setOrderTime(simpleDateFormat.format(record.getOrderTime()));
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", record.getUserId()));
            orderVO.setUserName(user.getUserName());
            orderVO.setUserAccount(user.getUserAccount());
            orderVO.setEmail(user.getEmail());
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    @Override
    public Integer getOrdersSize(String userId, String startTime, String endTime, String money1,String money2) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        //最新时间的排在前面
        orderQueryWrapper.orderByDesc("order_time");
        //用户账号过滤
        if(userId!=null&&!userId.equals("")){
            orderQueryWrapper.eq("user_id",userId);
        }
        //大于最早时间过滤
        if(startTime!=null&&!startTime.equals("")){
            orderQueryWrapper.ge("order_time",startTime);
        }
        //小于最晚时间过滤
        if(endTime!=null&&!endTime.equals("")){
            orderQueryWrapper.le("order_time",endTime);
        }
        //大于最小金额money1过滤
        if(money1!=null&&!money1.equals("")){
            orderQueryWrapper.ge("order_amount",money1);
        }
        //小于最大金额money2过滤
        if(money2!=null&&!money2.equals("")){
            orderQueryWrapper.le("order_amount",money2);
        }
        List<Order> orders = baseMapper.selectList(orderQueryWrapper);
        return orders.size();
    }

    @Override
    @Transactional
    public Integer deleteOrder(String id) {
        //逻辑删除订单
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("id",id);
        Order order = baseMapper.selectOne(orderQueryWrapper);
        order.setIsDelete(1);
        return baseMapper.updateById(order);
    }
}
