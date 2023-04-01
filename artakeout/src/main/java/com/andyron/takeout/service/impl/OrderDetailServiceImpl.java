package com.andyron.takeout.service.impl;

import com.andyron.takeout.entity.OrderDetail;
import com.andyron.takeout.mapper.OrderDetailMapper;
import com.andyron.takeout.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}