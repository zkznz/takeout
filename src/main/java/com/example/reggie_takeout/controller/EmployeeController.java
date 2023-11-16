package com.example.reggie_takeout.controller;

import com.example.reggie_takeout.common.R;
import com.example.reggie_takeout.dto.EmployeeLoginDto;
import com.example.reggie_takeout.entity.Employee;
import com.example.reggie_takeout.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody EmployeeLoginDto employeeLoginDto){
       return employeeService.login(request, employeeLoginDto);
    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.removeAttribute("employee");
        return R.success("退出成功");
    }
}
