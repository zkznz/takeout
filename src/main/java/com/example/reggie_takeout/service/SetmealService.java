package com.example.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie_takeout.dto.SetmealDto;
import com.example.reggie_takeout.entity.Setmeal;

import javax.servlet.http.HttpServletRequest;


public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(HttpServletRequest request, SetmealDto setmealDto);
}
