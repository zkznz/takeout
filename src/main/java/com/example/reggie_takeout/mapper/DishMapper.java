package com.example.reggie_takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie_takeout.entity.Dish;
import com.example.reggie_takeout.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
