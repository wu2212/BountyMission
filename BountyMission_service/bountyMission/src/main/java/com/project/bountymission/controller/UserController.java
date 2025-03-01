package com.project.bountymission.controller;

import com.project.bountymission.mapper.UserMapper;
import com.project.bountymission.pojo.entity.School;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    School get(){
        System.out.println(userMapper.getSchool());
       return userMapper.getSchool();
    }
}
