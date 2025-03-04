package com.project.bountymission.mapper;

import com.project.bountymission.pojo.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {


    List<School> schoolList(String keyword);

}
