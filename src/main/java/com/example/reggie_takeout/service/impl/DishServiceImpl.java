package com.example.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_takeout.entity.Dish;
import com.example.reggie_takeout.entity.Setmeal;
import com.example.reggie_takeout.mapper.DishMapper;
import com.example.reggie_takeout.mapper.SetmealMapper;
import com.example.reggie_takeout.service.DishService;
import com.example.reggie_takeout.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
