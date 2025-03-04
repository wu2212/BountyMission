package com.project.bountymission.controller;

import com.project.bountymission.mapper.UserMapper;
import com.project.bountymission.pojo.Result;
import com.project.bountymission.pojo.dto.UserRegisterDto;
import com.project.bountymission.pojo.entity.School;
import com.project.bountymission.service.UserService;
import com.project.bountymission.utils.SmsService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * 查询学校名单
     * @param keyword
     * @return
     */
    @GetMapping("/school")
    public Result<List<School>> getSchool(@RequestParam String keyword) {
      /*  String phone = "17508230617";
        smsService.sendVerificationCode(phone);*/
        List<School> schoolList = userService.schoolList(keyword);
        /*System.out.println(schoolList.size());
        for (School school : schoolList) {
            System.out.println(school.getSchoolName());
        }*/
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
        System.out.println(phone);
        return Result.success();
    }

    /**
     * 注册用户信息
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        System.out.println(userRegisterDto.getCode());
        System.out.println(userRegisterDto.toString());

        return Result.success();
    }
}
