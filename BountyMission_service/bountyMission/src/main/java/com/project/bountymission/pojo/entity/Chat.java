package com.project.bountymission.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Chat {
    private Integer id; // 聊天ID（主键）
    private Integer fromUser; // 发送者ID（外键）
    private Integer toUser; // 接收者ID（外键）
    private String content; // 聊天内容
    private LocalDateTime createdAt; // 创建时间
}
