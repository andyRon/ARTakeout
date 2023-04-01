package com.andyron.takeout.mapper;

import com.andyron.takeout.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author andyron
 **/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}