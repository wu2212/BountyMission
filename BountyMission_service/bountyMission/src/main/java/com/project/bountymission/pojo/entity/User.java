package com.project.bountymission.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id; // 用户ID（主键）
    private String username; // 用户名（手机号）
    private String password; // 密码（BCrypt加密）
    private String nickname; // 昵称
    private String avatar; // 头像URL
    private Integer schoolId; // 学校ID（外键）
    private Integer creditScore; // 信用分（默认100）
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
}
