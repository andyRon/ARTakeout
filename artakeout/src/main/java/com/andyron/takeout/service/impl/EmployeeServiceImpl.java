package com.andyron.takeout.service.impl;

import com.andyron.takeout.entity.Employee;
import com.andyron.takeout.mapper.EmployeeMapper;
import com.andyron.takeout.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author andyron
 **/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
