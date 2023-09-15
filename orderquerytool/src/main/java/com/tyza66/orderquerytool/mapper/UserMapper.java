package com.tyza66.orderquerytool.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tyza66.orderquerytool.pojo.User;
import com.tyza66.orderquerytool.vo.OrderVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author: tyza66
 * Date: 2023/9/15 13:52
 * Github: https://github.com/tyza66
 **/

public interface UserMapper extends BaseMapper<User> {
    //这个是基本的情况下查询所需要的表单信息 但是我们不使用这个 就是如果问sql怎么写就拿出来这个
    @Select("SELECT a.user_id as user_id,\n" +
            "\t\t\tb.user_account as user_account,\n" +
            "\t\t\tb.user_name as user_name,\n" +
            "\t\t\tb.email as email,\n" +
            "\t\t\ta.order_id as order_id,\n" +
            "\t\t\ta.order_time as order_time,\n" +
            "\t\t\ta.order_amount as order_amount\n" +
            "FROM `order` a left join `user` b on a.user_id = b.user_id")
    List<OrderVO> selectAllOrder();
}
