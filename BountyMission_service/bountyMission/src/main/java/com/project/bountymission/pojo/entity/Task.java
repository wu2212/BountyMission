package com.project.bountymission.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Task {
    private Integer id; // 任务ID（主键）
    private String title; // 任务标题
    private String description; // 任务描述
    private Integer category; // 任务分类（生活、学习等）
    private Integer reward; // 赏金金额
    private Integer status; // 任务状态（待接单等）
    private Integer publisherId; // 发布者ID（外键）
    private Integer acceptorId; // 接单者ID（外键）
    private LocalDateTime publishTime; // 发布时间
    private LocalDateTime deadline; // 截止时间
    private Integer priority; // 任务优先级（普通、加急）
    private String images; // 任务图片（JSON数组）
    private String notes; // 任务备注
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
}
