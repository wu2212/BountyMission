package com.project.bountymission;

import com.project.bountymission.controller.UserController;
import com.project.bountymission.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class BountyMissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BountyMissionApplication.class, args);
    }


}
