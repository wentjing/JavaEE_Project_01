package com.wyzxw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyzxw.entity.Depart;
import com.wyzxw.entity.Employee;
import com.wyzxw.entity.EmployeeExample;
import com.wyzxw.mapper.DepartMapper;
import com.wyzxw.mapper.EmployeeMapper;
import com.wyzxw.service.EmployeeService;
import com.wyzxw.vo.ConditionVo;
import com.wyzxw.vo.EmployeeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service //告诉框架（springmvc）这是一个业务类
@Transactional //开启事务处理
public class EmployeeServiceImpl implements EmployeeService {
   @Autowired //该注解会自动转配一个EmployeeMapper的实现类，赋值给employeeMapper，相当于EmployeeMapper employeeMapper = new 相当于EmployeeMapperImpl();
    private EmployeeMapper employeeMapper;
   @Autowired
    private DepartMapper departMapper;

    @Override
    public List<Employee> getEmpList() {
        return employeeMapper.selectByExample(null); //根据条件查询多条记录，当参数为null的时候就是查询全部
    }

    @Override
    public boolean addEmp(Employee employee) {
        return employeeMapper.insertSelective(employee)>0 ? true:false;
    }

    @Override
    public boolean updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee)>0 ? true : false;
    }

    @Override
    public boolean deleteEmp(Integer empId) {
        return employeeMapper.deleteByPrimaryKey(empId)>0 ? true:false;
    }

    @Override
    public Employee getEmpById(Integer empId) {
        return employeeMapper.selectByPrimaryKey(empId);
    }

    /**
     * 分布查询：
     * 1、根据empId查询出Employee对象，然后把Employee对象的每个字段拷贝到EmployeeVo中和Employee相同的字段中；
     * 2、从Employee中获得depid,根据depid查询对应的Depart对象，然后将Depart对象中的depname拷贝到EmployeeVo中的depname.
     * @param empId
     * @return
     */
    @Override
    public EmployeeVo getEmp(Integer empId) {
        EmployeeVo employeeVo = new EmployeeVo();
        Employee employee = employeeMapper.selectByPrimaryKey(empId);
        //拷贝数据
        BeanUtils.copyProperties(employee,employeeVo);
        Integer depid = employee.getDepid();
        Depart depart = departMapper.selectByPrimaryKey(depid);
        employeeVo.setDepname(depart.getDepname());
        return employeeVo;
    }

    /**
     * employeeMapper的selectByExample(),如果添加查询参数应该把参数放到一个对象中，整个对象就是EmployeeExample
     * 参数不能直接放到EmployeeExample，而是通过它的内部类EmployeeExample.Criteria来完成，整个类是一个查询条件封装
     * 的容器，所有的条件放到EmployeeExample.Criteria里面即可。
     * @param pageNum
     * @param pageSize
     * @param conditionVo
     * @return
     */
    @Override
    public PageInfo<EmployeeVo> getListByPage(Integer pageNum, Integer pageSize, ConditionVo conditionVo) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        if(null != conditionVo){
           if(conditionVo.getDepid()!=null && conditionVo.getDepid()!=-1){
               criteria.andDepidEqualTo(conditionVo.getDepid());
           }
           if(StringUtils.isNoneBlank(conditionVo.getAddress())){
               criteria.andAddressLike("%" + conditionVo.getAddress() + "%");
           }
           if(null !=conditionVo.getMin_bsaralry()){
               criteria.andBsaralryGreaterThanOrEqualTo(conditionVo.getMin_bsaralry());
           }
           if(null !=conditionVo.getMax_bsaralry()){
               criteria.andBsaralryLessThanOrEqualTo(conditionVo.getMax_bsaralry());
           }
        }
        //关键步骤：分页查询的步骤
        PageHelper.startPage(pageNum,pageSize);//第1步
        List<Employee> employeeList = employeeMapper.selectByExample(example);//第2步
        //把List<Employee>转化成List<EmployeeVo>
        List<EmployeeVo> list = new ArrayList<>();
        if(null != employeeList && employeeList.size()>0){
               for(Employee employee:employeeList){
                   EmployeeVo employeeVo = new EmployeeVo();
                   BeanUtils.copyProperties(employee,employeeVo);
                   Depart depart = departMapper.selectByPrimaryKey(employee.getDepid());
                   employeeVo.setDepname(depart.getDepname());
                   list.add(employeeVo);
               }
        }
        //第3步
        PageInfo<Employee> info = new PageInfo<>(employeeList);
        PageInfo<EmployeeVo> pageInfo = new PageInfo<>(list);
        //拷贝当前页和总页数，由于pageHelper使用了AOPUtils工具类，这个就涉及到spring AOP的技术，页码发生了变化。
        pageInfo.setPageNum(info.getPageNum());
        pageInfo.setPages(info.getPages());
        return pageInfo;
    }
}
