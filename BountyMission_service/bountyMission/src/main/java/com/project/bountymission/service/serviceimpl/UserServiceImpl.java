package com.project.bountymission.service.serviceimpl;

import com.project.bountymission.exception.AccountExistsException;
import com.project.bountymission.exception.AccountNotExistsException;
import com.project.bountymission.exception.PasswordMismatchException;
import com.project.bountymission.exception.VerificationCodeException;
import com.project.bountymission.mapper.UserMapper;
import com.project.bountymission.pojo.Result;
import com.project.bountymission.pojo.dto.UserLoginDto;
import com.project.bountymission.pojo.dto.UserRegisterDto;
import com.project.bountymission.pojo.dto.UserSmsLoginDto;
import com.project.bountymission.pojo.entity.School;
import com.project.bountymission.pojo.entity.User;
import com.project.bountymission.service.UserService;
import com.project.bountymission.utils.PasswordUtil;
import com.project.bountymission.utils.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SmsService smsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 查询学校信息
     * @param keyword
     * @return
     */
    @Override
    public List<School> schoolList(String keyword) {
        return userMapper.schoolList(keyword);
    }

    /**
     * 用户注册功能
     * @param userRegisterDto
     */
    @Override
    public void userRegister(UserRegisterDto userRegisterDto) {
        Integer codeint = userRegisterDto.getCode();
        String code = String.valueOf(codeint);
        String phone = userRegisterDto.getUsername();
        //检验验证码
        boolean b = smsService.verifyCode(phone, code);
        //判断验证码是否验证通过
        if (!b){
            throw new VerificationCodeException();
        }
        //判断账号是否已经存在
        User user = userMapper.selectByPhone(phone);
        if (user != null){
            //存在返回已存在异常
            throw new AccountExistsException();
        }
        //不存在注册用户信息
        userRegisterDto.setCreatedAt(LocalDateTime.now());
        userRegisterDto.setUpdatedAt(LocalDateTime.now());
        //密码加密处理
        String encode = passwordEncoder.encode(userRegisterDto.getPassword());
        userRegisterDto.setPassword(encode);
        //调取方法存入数据
        userMapper.register(userRegisterDto);
    }


    /**
     * 账号密码登录
     * @param userLoginDto
     */
    @Override
    public void login(UserLoginDto userLoginDto) {
        //查询是否有账号
        User user = userMapper.selectByPhone(userLoginDto.getUsername());
        //没有账号返回账号不存在异常，请先注册
        if (user == null){
            throw new AccountNotExistsException();
        }
        //查询密码是否一致
        /*if (!passwordEncoder.encode(userLoginDto.getPassword()).equals(user.getPassword())){
            throw new PasswordMismatchException();
        }*/
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            throw new PasswordMismatchException();
        }
    }

    /**
     * 短信验证码登录
     * @param userSmsLoginDto
     */
    @Override
    public void smslogin(UserSmsLoginDto userSmsLoginDto) {
        //检验数据库是否有当前用户
        User user = userMapper.selectByPhone(userSmsLoginDto.getPhone());
        //没有账号返回账号不存在异常，请先注册
        if (user == null){
            throw new AccountNotExistsException();
        }
        //检验验证码是否符合
        if (!smsService.verifyCode(userSmsLoginDto.getPhone(), userSmsLoginDto.getCode())){
            throw new VerificationCodeException();
        }

    }
}
