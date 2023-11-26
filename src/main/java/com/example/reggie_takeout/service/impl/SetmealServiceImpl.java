package com.example.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_takeout.dto.SetmealDto;
import com.example.reggie_takeout.entity.Setmeal;
import com.example.reggie_takeout.entity.SetmealDish;
import com.example.reggie_takeout.mapper.SetmealMapper;
import com.example.reggie_takeout.service.SetmealDishService;
import com.example.reggie_takeout.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    @Override
    public void saveWithDish(HttpServletRequest request,SetmealDto setmealDto) {
        Long empId=(Long) request.getSession().getAttribute("employee");
        setmealDto.setCreateUser(empId);
        setmealDto.setUpdateUser(empId);
        this.save(setmealDto);
        List<SetmealDish> setmealDishList = setmealDto.getSetmealDishes();
        setmealDishList=setmealDishList.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            item.setCreateUser(empId);
            item.setUpdateUser(empId);
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishList);
    }
}
