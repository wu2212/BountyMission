package com.project.bountymission.mapper;

import com.project.bountymission.pojo.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testDatabaseConnection() {
        School users = userMapper.getSchool(); // 调用Mapper方法
        if (users != null) {
            System.out.println("数据库连接成功，查询到用户数据：");
            System.out.println(users);

        } else {
            System.out.println("数据库连接成功，但未查询到用户数据。");
        }
    }
}
