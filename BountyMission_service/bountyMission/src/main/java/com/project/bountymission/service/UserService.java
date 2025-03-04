package com.project.bountymission.service;

import com.project.bountymission.pojo.entity.School;

import java.util.List;

public interface UserService {

    /**
     * 获取所有学校列表
     */
    List<School> schoolList(String keyword);
}
