package com.project.bountymission.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User_wallet {
    private Integer id; // 钱包ID（主键）
    private Integer userId; // 用户ID（外键）
    private Integer balance; // 可用余额
    private Integer frozenBalance; // 冻结金额
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间
}
