package com.andyron.takeout.service;

import com.andyron.takeout.dto.SetmealDto;
import com.andyron.takeout.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author andyron
 **/
public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，已经相应的套餐和菜品的关联关系
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐(只有停售的可以删除)，同时删除套餐和菜品的关联数据
     */
    void removeWithDish(List<Long> ids);
}
