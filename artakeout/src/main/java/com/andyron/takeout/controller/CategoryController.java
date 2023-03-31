package com.andyron.takeout.controller;

import com.andyron.takeout.common.R;
import com.andyron.takeout.entity.Category;
import com.andyron.takeout.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author andyron
 **/
@Api(tags = "分类管理")
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("新增分类")
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category.toString());
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        Page<Category> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(Category::getSort);
        categoryService.page(pageInfo, qw);
        return R.success(pageInfo);
    }

    @ApiOperation("删除分类")
    @DeleteMapping
    public R<String> remove(Long id) {
        log.info("分类id:{}", id);
        categoryService.remove(id);
        return R.success("分类信息删除成功");
    }

    @ApiOperation("根据id修改分类信息")
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("修改分类信息：{}", category);
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }

    @ApiOperation("根据条件查询分类数据")
    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        LambdaQueryWrapper<Category> qw = new LambdaQueryWrapper<>();
        qw.eq(category.getType() != null, Category::getType, category.getType());
        qw.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(qw);
        return R.success(list);
    }
}
