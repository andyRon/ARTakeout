package com.andyron.takeout.service.impl;

import com.andyron.takeout.common.CustomException;
import com.andyron.takeout.entity.Category;
import com.andyron.takeout.entity.Dish;
import com.andyron.takeout.entity.Setmeal;
import com.andyron.takeout.mapper.CategoryMapper;
import com.andyron.takeout.service.CategoryService;
import com.andyron.takeout.service.DishService;
import com.andyron.takeout.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author andyron
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，条件判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishQW = new LambdaQueryWrapper<>();
        dishQW.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishQW);
        // 如当前分类关联了菜品就抛出业务异常
        if (count1 > 0) {
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealQW = new LambdaQueryWrapper<>();
        setmealQW.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealQW);
        // 如当前分类关联了套餐就抛出业务异常
        if (count2 > 0) {
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        // 正常删除分类
        super.removeById(id);
    }
}
