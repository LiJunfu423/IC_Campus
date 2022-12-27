package com.ic.intelligentcampus.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ic.intelligentcampus.Entity.User;
import com.ic.intelligentcampus.Mapper.UserMapper;
import com.ic.intelligentcampus.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
