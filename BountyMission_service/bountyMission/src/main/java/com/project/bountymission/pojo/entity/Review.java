package com.project.bountymission.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private Integer id; // 评价ID（主键）
    private Integer taskId; // 任务ID（外键）
    private Integer fromUser; // 评价者ID（外键）
    private Integer toUser; // 被评价者ID（外键）
    private Integer rating; // 评分（1-5）
    private String comment; // 评价内容
    private LocalDateTime createdAt;
}
