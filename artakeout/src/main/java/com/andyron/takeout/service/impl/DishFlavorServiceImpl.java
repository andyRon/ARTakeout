package com.andyron.takeout.service.impl;

import com.andyron.takeout.entity.DishFlavor;
import com.andyron.takeout.mapper.DishFlavorMapper;
import com.andyron.takeout.service.DishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author andyron
 **/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
