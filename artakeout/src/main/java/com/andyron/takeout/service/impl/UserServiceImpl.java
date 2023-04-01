package com.andyron.takeout.service.impl;

import com.andyron.takeout.entity.User;
import com.andyron.takeout.mapper.UserMapper;
import com.andyron.takeout.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author andyron
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
