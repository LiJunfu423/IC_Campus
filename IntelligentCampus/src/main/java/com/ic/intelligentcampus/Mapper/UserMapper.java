package com.ic.intelligentcampus.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ic.intelligentcampus.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
