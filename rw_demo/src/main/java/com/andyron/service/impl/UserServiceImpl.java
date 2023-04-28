package com.andyron.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.andyron.entity.User;
import com.andyron.mapper.UserMapper;
import com.andyron.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
