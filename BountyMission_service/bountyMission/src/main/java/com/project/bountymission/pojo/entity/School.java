package com.project.bountymission.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    private Integer id; // 学校ID（主键）
    private String schoolName; // 学校名称
}
