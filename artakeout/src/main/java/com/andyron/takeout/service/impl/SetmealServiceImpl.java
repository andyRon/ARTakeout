package com.andyron.takeout.service.impl;

import com.andyron.takeout.entity.Setmeal;
import com.andyron.takeout.mapper.SetmealMapper;
import com.andyron.takeout.service.SetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author andyron
 **/
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
