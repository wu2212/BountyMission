package com.project.bountymission.controller;

import com.project.bountymission.mapper.UserMapper;
import com.project.bountymission.pojo.Result;
import com.project.bountymission.pojo.dto.UserLoginDto;
import com.project.bountymission.pojo.dto.UserRegisterDto;
import com.project.bountymission.pojo.dto.UserSmsLoginDto;
import com.project.bountymission.pojo.entity.School;
import com.project.bountymission.service.UserService;
import com.project.bountymission.utils.PasswordUtil;
import com.project.bountymission.utils.SmsService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 查询学校名单
     * @param keyword
     * @return
     */
    @GetMapping("/school")
    public Result<List<School>> getSchool(@RequestParam() String keyword) {
        List<School> schoolList = userService.schoolList(keyword);
        return Result.success(schoolList);
    }

    /**
     * 发送验证码
     * @param params
     * @return
     */
    @PostMapping("/sms")
    public Result<String> sendSms(@RequestBody Map<String, Object> params) {
        String phone = (String) params.get("phone");
        smsService.sendVerificationCode(phone);
        System.out.println(phone);
        return Result.success();
    }

    /**
     * 注册用户信息
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.userRegister(userRegisterDto);
      return Result.success("注册成功，请登录");
    }

    /**|
     * 账号密码登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        userService.login(userLoginDto);
        return Result.success("登录成功");
    }

    /**
     * 短信验证码登录
     */
    @PostMapping("/smslogin")
    public Result smsLogin(@RequestBody UserSmsLoginDto userSmsLoginDto) {
        userService.smslogin(userSmsLoginDto);
        return Result.success();
    }
}
