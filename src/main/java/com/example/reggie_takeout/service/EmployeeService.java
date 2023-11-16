package com.example.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie_takeout.common.R;
import com.example.reggie_takeout.dto.EmployeeLoginDto;
import com.example.reggie_takeout.entity.Employee;

import javax.servlet.http.HttpServletRequest;


public interface EmployeeService extends IService<Employee> {
   public R<Employee> login(HttpServletRequest request, EmployeeLoginDto employeeLoginDto);
}
