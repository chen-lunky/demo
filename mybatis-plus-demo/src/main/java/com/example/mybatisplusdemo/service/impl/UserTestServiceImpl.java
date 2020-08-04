package com.example.mybatisplusdemo.service.impl;

import com.example.mybatisplusdemo.entity.UserTest;
import com.example.mybatisplusdemo.mapper.UserTestMapper;
import com.example.mybatisplusdemo.service.USERTESTervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author example
 * @since 2020-08-03
 */
@Service
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper, UserTest> implements USERTESTervice {

}
