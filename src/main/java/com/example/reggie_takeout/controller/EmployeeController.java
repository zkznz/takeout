package com.example.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie_takeout.common.BaseContext;
import com.example.reggie_takeout.common.R;
import com.example.reggie_takeout.dto.EmployeeLoginDto;
import com.example.reggie_takeout.entity.Employee;
import com.example.reggie_takeout.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //添加员工
    @PostMapping
    public R<String> add(HttpServletRequest request,@RequestBody Employee employee){
        employeeService.add(request,employee);
        return R.success("添加成功");
    }
    @PostMapping("/login")
    public R <Employee> login(HttpServletRequest request, @RequestBody EmployeeLoginDto employeeLoginDto){
       return employeeService.login(request, employeeLoginDto);
    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.removeAttribute("employee");
        return R.success("退出成功");
    }
    //分页查询员工信息
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //创建条件构造器
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotBlank(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行分页查询
         employeeService.page(pageInfo,queryWrapper);
         return R.success(pageInfo);
    }
    //通过id修改员工信息
    @PutMapping
    public R<String> updateById(HttpServletRequest request,@RequestBody Employee employee){
        Long userId=(Long)request.getSession().getAttribute("employee");
        employee.setUpdateUser(userId);
        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }
    //通过id查找员工信息
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee= employeeService.getById(id);
        if(employee!=null){
            return R.success(employee);
        }
        else
            return R.error("没有查询到对应员工信息");
    }
}
