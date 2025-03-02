package com.project.bountymission.service.serviceimpl;

import com.project.bountymission.mapper.AdminMapper;
import com.project.bountymission.pojo.entity.Admin;
import com.project.bountymission.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    //处理登录逻辑
    @Override
    public Admin login(Admin admin) {
        Admin loginadmin = adminMapper.login(admin);
        if (loginadmin == null) {
            return null;
        }
        if (!loginadmin.getPassword().equals(admin.getPassword())) {
            return null;
        }
        return loginadmin;
    }
}
