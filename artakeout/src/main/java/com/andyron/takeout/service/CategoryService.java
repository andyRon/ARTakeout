package com.andyron.takeout.service;

import com.andyron.takeout.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author andyron
 **/
public interface CategoryService extends IService<Category> {

    void remove(Long id);
}
