package com.andyron.takeout.service.impl;

import com.andyron.takeout.entity.AddressBook;
import com.andyron.takeout.mapper.AddressBookMapper;
import com.andyron.takeout.service.AddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
