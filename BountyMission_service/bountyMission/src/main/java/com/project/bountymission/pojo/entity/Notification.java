package com.project.bountymission.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification {
    private Integer id; // 通知ID（主键）
    private Integer userId; // 接收用户ID（外键）
    private String content; // 通知内容
    private Integer type; // 通知类型（任务、系统等）
    private LocalDateTime createdAt; // 创建时间
}
