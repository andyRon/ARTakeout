package com.andyron.takeout.service;

import com.andyron.takeout.dto.DishDto;
import com.andyron.takeout.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author andyron
 **/
public interface DishService extends IService<Dish> {
    /**
     * 新增菜品，同时插入菜品对应的口味
     */
    void saveWithFlavor(DishDto dishDto);

    /**
     * 根据id查询菜品信息和对应口味信息
     */
    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}
