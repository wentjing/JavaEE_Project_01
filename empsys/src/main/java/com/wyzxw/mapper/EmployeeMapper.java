package com.wyzxw.mapper;

import com.wyzxw.entity.Employee;
import com.wyzxw.entity.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);//根据条件删除

    int deleteByPrimaryKey(Integer empid);//根据主键删除

    int insert(Employee record);

    int insertSelective(Employee record);//添加的方法

    List<Employee> selectByExample(EmployeeExample example);//根据条件查询多条记录，当参数为null的时候就是查询全部

    Employee selectByPrimaryKey(Integer empid);//根据主键查询单条记录

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);//修改记录

    int updateByPrimaryKey(Employee record);
}