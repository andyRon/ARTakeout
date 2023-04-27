package com.andyron.takeout.controller;

import com.andyron.takeout.common.R;
import com.andyron.takeout.dto.SetmealDto;
import com.andyron.takeout.entity.Category;
import com.andyron.takeout.entity.Setmeal;
import com.andyron.takeout.service.CategoryService;
import com.andyron.takeout.service.SetmealDishService;
import com.andyron.takeout.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andyron
 **/
@Api("套餐管理")
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("新增套餐")
    @PostMapping
    @CacheEvict(value = "setmealCache", allEntries = true)
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("套餐信息：{}", setmealDto.toString());
        setmealService.saveWithDish(setmealDto);

        return R.success("新增套餐成功");
    }

    @ApiOperation("分页查询套餐")
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Setmeal> qw = new LambdaQueryWrapper<>();
        qw.like(name != null, Setmeal::getName, name);
        qw.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, qw);

        Page<SetmealDto> dtoPage = new Page<>();
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Category category = categoryService.getById(item.getCategoryId());
            if (category != null) {
                setmealDto.setCategoryName(category.getName());
            }
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }
    @ApiOperation("删除套餐")
    @DeleteMapping
    @CacheEvict(value = "setmealCache", allEntries = true)  // 删除一个套餐时，删除所有套餐缓存
    public R<String > remove(@RequestParam List<Long> ids) {
        log.info("ids:{}", ids);
        setmealService.removeWithDish(ids);
        return R.success("套餐数据删除成功");
    }

    @ApiOperation("条件查询套餐数据")
    @GetMapping("/list")
    @Cacheable(value = "setmealCache", key = "#setmeal.categoryId + '_' + #setmeal.status")
    public R<List<Setmeal>> list(Setmeal setmeal) {  // `@RequestBody`表示前端传输过来的是json对象，这里是请求行里的键值对直接使用实体对象就可以
        LambdaQueryWrapper<Setmeal> qw = new LambdaQueryWrapper<>();
        qw.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
        qw.eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus());
        qw.orderByDesc(Setmeal::getUpdateTime);
        return R.success(setmealService.list(qw));
    }
}
