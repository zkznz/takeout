package com.example.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_takeout.common.R;
import com.example.reggie_takeout.dto.EmployeeLoginDto;
import com.example.reggie_takeout.entity.Employee;
import com.example.reggie_takeout.mapper.EmployeeMapper;
import com.example.reggie_takeout.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public R<Employee> login(HttpServletRequest request, EmployeeLoginDto employeeLoginDto){
        //密码md5加密
        String password= employeeLoginDto.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        //从数据库获取用户信息
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employeeLoginDto.getUsername());
        Employee emp= employeeMapper.selectOne(queryWrapper);
        if(emp==null)
            return R.error("登录失败");
        if(!emp.getPassword().equals(password)){
            return R.error("密码错误");
        }
        if(emp.getStatus()==0){
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    @Override
    public void add(HttpServletRequest request, Employee employee) {
        Long empId=(Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //设置初始密码
        String md5Pwd=DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(md5Pwd);
        employeeMapper.insert(employee);
    }
}
