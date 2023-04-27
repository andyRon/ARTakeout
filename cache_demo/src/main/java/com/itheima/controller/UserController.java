package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.entity.User;
import com.itheima.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private UserService userService;

    /**
     * CachePut：将方法返回值放入缓存
     * value：缓存的名称，每个缓存名称下面可以有多个key，相当于某一类缓存
     * key：缓存的key   SpEL
     */
    @CachePut(value = "userCache", key = "#user.id")
    @PostMapping
    public User save(User user){ // 没有@RequestBody，不需要JSON数据，提交普通的表单数据就可以
        userService.save(user);
        return user;
    }

    /**
     * CacheEvict：清理指定缓存
     * value：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     *  第一个参数的几种写法 #p0 #root.args[0] #id
     */
    @CacheEvict(value = "userCache", key = "#p0")  // 0是参数位置
    //@CacheEvict(value = "userCache",key = "#root.args[0]")
    //@CacheEvict(value = "userCache",key = "#id")  // 要和形参名相同
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.removeById(id);
    }

    //@CacheEvict(value = "userCache",key = "#p0.id")
    //@CacheEvict(value = "userCache",key = "#user.id")
    //@CacheEvict(value = "userCache",key = "#root.args[0].id")
    @CacheEvict(value = "userCache", key = "#result.id") // 更新时也删除缓存
    @PutMapping
    public User update(User user){
        userService.updateById(user);
        return user;
    }

    /**
     * Cacheable：在方法执行前spring先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；若没有数据，调用方法并将方法返回值放到缓存中
     * value：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     * condition：条件，满足条件时才缓存数据
     * unless：满足条件则不缓存
     * 注意condition不支持result，而unless支持
     */
    @Cacheable(value = "userCache", key = "#id", unless = "#result == null")
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        User user = userService.getById(id);
        return user;
    }

    /**
     * 条件查询情况
     * 不同的条件，对应不同的key
     */
    @Cacheable(value = "userCache",key = "#user.id + '_' + #user.name")
    @GetMapping("/list")
    public List<User> list(User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(user.getId() != null, User::getId,user.getId());
        queryWrapper.eq(user.getName() != null, User::getName,user.getName());
        List<User> list = userService.list(queryWrapper);
        return list;
    }
}
