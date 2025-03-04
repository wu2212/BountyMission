package com.project.bountymission.service.serviceimpl;

import com.project.bountymission.mapper.UserMapper;
import com.project.bountymission.pojo.entity.School;
import com.project.bountymission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<School> schoolList(String keyword) {
        return userMapper.schoolList(keyword);
    }
    /**
     * 获取学校列表
     * @return
     */


}
