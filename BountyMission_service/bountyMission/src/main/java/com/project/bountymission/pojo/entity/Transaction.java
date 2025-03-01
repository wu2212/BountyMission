package com.project.bountymission.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private Integer id; // 交易ID（主键）
    private Integer fromUser; // 支出用户ID（外键）
    private Integer toUser; // 收入用户ID（外键）
    private Integer amount; // 交易金额
    private Integer type; // 交易类型（赏金、充值等）
    private Integer taskId; // 关联任务ID（外键）
    private LocalDateTime createdAt;
}
