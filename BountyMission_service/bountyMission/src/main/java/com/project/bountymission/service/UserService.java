package com.project.bountymission.service;

import com.project.bountymission.pojo.dto.UserLoginDto;
import com.project.bountymission.pojo.dto.UserRegisterDto;
import com.project.bountymission.pojo.dto.UserSmsLoginDto;
import com.project.bountymission.pojo.entity.School;

import java.util.List;

public interface UserService {

    /**
     * 获取所有学校列表
     */
    List<School> schoolList(String keyword);


    /**
     * 用户注册
     */
    void userRegister(UserRegisterDto userRegisterDto);

    /**
     * 用户账号密码登录
     * @param userLoginDto
     */
    void login(UserLoginDto userLoginDto);

    /**
     * 短信验证码登录
     * @param userSmsLoginDto
     */
    void smslogin(UserSmsLoginDto userSmsLoginDto);
}
