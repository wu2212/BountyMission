package com.project.bountymission.mapper;

import com.project.bountymission.pojo.dto.UserRegisterDto;
import com.project.bountymission.pojo.entity.School;
import com.project.bountymission.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询学校列表
     * @param keyword
     * @return
     */
    List<School> schoolList(String keyword);

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    @Select("select * from user where username = #{phone}")
    User selectByPhone(String phone);

    /**
     * 注册，插入用户数据
     * @param userRegisterDto
     */
    @Insert("insert into user (username,password,nickname,school_id,created_at,updated_at)" +
            "values (#{username},#{password},#{nickname},#{school},#{createdAt},#{updatedAt})")
    void register(UserRegisterDto userRegisterDto);
}
