package com.andyron.takeout.mapper;

import com.andyron.takeout.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author andyron
 **/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
