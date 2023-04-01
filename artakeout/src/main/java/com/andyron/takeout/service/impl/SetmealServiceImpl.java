package com.andyron.takeout.service.impl;

import com.andyron.takeout.common.CustomException;
import com.andyron.takeout.dto.SetmealDto;
import com.andyron.takeout.entity.Setmeal;
import com.andyron.takeout.entity.SetmealDish;
import com.andyron.takeout.mapper.SetmealMapper;
import com.andyron.takeout.service.SetmealDishService;
import com.andyron.takeout.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andyron
 **/
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId((setmealDto.getId()));
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }

    @Transactional
    @Override
    public void removeWithDish(List<Long> ids) {
        // 查询套餐状态，判断是否可以删除
        LambdaQueryWrapper<Setmeal> qw = new LambdaQueryWrapper<>();
        qw.in(Setmeal::getId, ids);
        qw.eq(Setmeal::getStatus, 1);
        int count = this.count(qw);
        if (count > 0) {
            throw  new CustomException("套餐正在售卖中，不能删除");
        }

        this.removeByIds(ids);
        LambdaQueryWrapper<SetmealDish> qw2 = new LambdaQueryWrapper<>();
        qw2.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(qw2);
    }
}
