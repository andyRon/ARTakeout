package com.andyron.takeout.service.impl;

import com.andyron.takeout.entity.ShoppingCart;
import com.andyron.takeout.mapper.ShoppingCartMapper;
import com.andyron.takeout.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
