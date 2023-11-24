package com.example.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie_takeout.common.R;
import com.example.reggie_takeout.entity.Category;
import com.example.reggie_takeout.entity.Employee;
import com.example.reggie_takeout.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/category")
public class CategoryController{
    @Autowired
    private CategoryService categoryService;
    //添加分类
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Category category){
        Long empId=(Long) request.getSession().getAttribute("employee");
        category.setCreateUser(empId);
        category.setUpdateUser(empId);
        categoryService.save(category);
        return R.success("新增分类成功");
    }
    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        Page pageInfo=new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

}
