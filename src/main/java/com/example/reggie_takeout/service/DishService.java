package com.example.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie_takeout.dto.DishDto;
import com.example.reggie_takeout.entity.Dish;
import com.example.reggie_takeout.entity.Setmeal;

import javax.servlet.http.HttpServletRequest;


public interface DishService extends IService<Dish> {
    void saveWithFlavor(HttpServletRequest request, DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);
}
