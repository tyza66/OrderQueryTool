package com.tyza66.orderquerytool.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tyza66.orderquerytool.pojo.User;
import com.tyza66.orderquerytool.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Author: tyza66
 * Date: 2023/9/15 14:00
 * Github: https://github.com/tyza66
 **/

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public JSON login(@RequestBody User user){
        JSONObject obj = JSONUtil.createObj();
        Boolean aBoolean = userService.checkUser(user.getUserAccount(), user.getPassword());
        if (aBoolean){
            obj.set("msg","ok");
            obj.set("code",200);
            StpUtil.login(0);
            obj.set("token",StpUtil.getTokenValue());
        }else {
            obj.set("msg","error");
            obj.set("code",501);
        }
        return obj;
    }

    @GetMapping("/check")
    public JSON checkLogin(){
        JSONObject obj = JSONUtil.createObj();
        boolean login = StpUtil.isLogin();
        if (login){
            obj.set("msg","ok");
            obj.set("code",200);
        }else {
            obj.set("msg","error");
            obj.set("code",501);
        }
        return obj;
    }

    @PostMapping("/register")
    public JSON register(@RequestBody User user){
        JSONObject obj = JSONUtil.createObj();
        Integer integer = userService.registerUser(user.getUserAccount(), user.getUserName(), user.getPassword(), user.getEmail());
        if(integer ==null){
            obj.set("msg","error");
            obj.set("code",501);
            obj.set("error","注册失败");
        }
        if (integer == 0){
            obj.set("msg","ok");
            obj.set("code",200);
        }else if (integer == 1){
            obj.set("msg","error");
            obj.set("code",501);
            obj.set("error","账号已存在");
        }else if (integer == 2){
            obj.set("msg","error");
            obj.set("code",501);
            obj.set("error","邮箱已存在");
        }else if (integer == 3){
            obj.set("msg","error");
            obj.set("code",501);
            obj.set("error","注册失败");
        }
        return obj;
    }


}
