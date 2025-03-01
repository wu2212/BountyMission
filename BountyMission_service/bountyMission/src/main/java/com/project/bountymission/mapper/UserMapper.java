package com.project.bountymission.mapper;

import com.project.bountymission.pojo.entity.School;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into school  (school_name) values ('苏州科技大学')")
    void insertUser();

    @Select("select * from school where id = 1")
    School getSchool();

}
