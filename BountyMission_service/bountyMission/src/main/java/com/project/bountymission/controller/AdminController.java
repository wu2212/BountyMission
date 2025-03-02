package com.project.bountymission.controller;


import com.project.bountymission.pojo.Result;
import com.project.bountymission.pojo.entity.Admin;
import com.project.bountymission.service.AdminService;
import com.project.bountymission.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        System.out.println("接收前端请求");
        System.out.println(admin);

        Admin loginadmin = adminService.login(admin);
        if (loginadmin != null) {
            //生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("user_id", loginadmin.getId());
            claims.put("username", loginadmin.getUsername());
            claims.put("roles", loginadmin.getPassword());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        else {
            return Result.fail("登录失败，账号或密码错误");
        }
    }
}
