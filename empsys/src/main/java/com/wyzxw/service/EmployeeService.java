package com.wyzxw.service;

import com.github.pagehelper.PageInfo;
import com.wyzxw.entity.Employee;
import com.wyzxw.vo.ConditionVo;
import com.wyzxw.vo.EmployeeVo;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getEmpList();
    public boolean addEmp(Employee employee);
    public boolean updateEmp(Employee employee);
    public boolean deleteEmp(Integer empId);
    public Employee getEmpById(Integer empId);
    public EmployeeVo getEmp(Integer empId);

    PageInfo<EmployeeVo> getListByPage(Integer pageNum, Integer pageSize, ConditionVo conditionVo);

}
