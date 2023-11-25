package com.example.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_takeout.dto.DishDto;
import com.example.reggie_takeout.entity.Dish;
import com.example.reggie_takeout.entity.DishFlavor;
import com.example.reggie_takeout.entity.Setmeal;
import com.example.reggie_takeout.mapper.DishMapper;
import com.example.reggie_takeout.mapper.SetmealMapper;
import com.example.reggie_takeout.service.DishFlavorService;
import com.example.reggie_takeout.service.DishService;
import com.example.reggie_takeout.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    @Override
    public void saveWithFlavor(HttpServletRequest request, DishDto dishDto) {
        Long dishId=dishDto.getId();
        Long empId=(Long) request.getSession().getAttribute("employee");
        //保存菜品基本信息
        dishDto.setCreateUser(empId);
        dishDto.setUpdateUser(empId);
        this.save(dishDto);
        //保存菜品口味数据到菜品口味数据表
        List<DishFlavor> flavors = dishDto.getFlavors();
       flavors=flavors.stream().map((item)->{
            item.setDishId(dishId);
            item.setCreateUser(empId);
            item.setUpdateUser(empId);
            return item;
        }).collect(Collectors.toList());
       dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息
        Dish dish=this.getById(id);
        DishDto dishDto=new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        //查询口味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,id);
        List<DishFlavor> flavorList = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavorList);
        return dishDto;
    }
}
