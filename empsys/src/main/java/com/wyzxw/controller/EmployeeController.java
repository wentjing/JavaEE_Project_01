package com.wyzxw.controller;

import com.github.pagehelper.PageInfo;
import com.wyzxw.entity.Depart;
import com.wyzxw.entity.Employee;
import com.wyzxw.service.DepartService;
import com.wyzxw.service.EmployeeService;
import com.wyzxw.vo.ConditionVo;
import com.wyzxw.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller //告诉框架这是控制器
public class EmployeeController {

    @Autowired //该注解会自动装配一个EmployeeService的实现类，赋值给employeeService，相当于EmployeeService employeeService = new 相当于EmployeeServiceImpl();
    private EmployeeService employeeService;
    @Autowired
    private DepartService departService;

    /**
     * 原来在servlet中会配置servlet-mapping url,现在它是通过
     * @RequestMapping来声明请求的url的
     * http://localhost:8080/list
     *
     */
    @RequestMapping(value = "/list")
    public String getEmpList(Map map){
        List<Employee> empList = employeeService.getEmpList();
        //原来我们在servlet中可以把数据存储在request和session中，现在存在map中，相当于存储到request作用域中。
        map.put("empList",empList);
        /**
         *   return "list"中的list称为视图的逻辑名，我们的目的是要找到物理视图名
         *   list.jsp是物理视图名
         *   <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
         * 		<property name="prefix" value="/WEB-INF/jsp/" />
         * 		<property name="suffix" value=".jsp" />
         * 	</bean>
         *
         * 	匹配方式：前缀 + 逻辑名 +后缀
         * 	   /WEB-INF/jsp/list.jsp
         */
        return "list";
    }

    /**
     *  @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum
     *  如果前台往后台入参的时候，传递了pageNum,那么就会把该pageNum给value中的pageNum,
     *  从而该pageNum就会赋值给Integer pageNum;如果没有传递到后台，这里加上一个属性defaultValue,
     *  那么defaultValue的值就会给value,进而参数Integer pageNum也是这个默认值。
     * @param conditionVo
     * @param pageNum
     * @param pageSize
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/pageList",method = {RequestMethod.GET,RequestMethod.POST})
    public String pageList(ConditionVo conditionVo,
                           @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                           ModelMap modelMap){
        PageInfo<EmployeeVo> pageInfo = employeeService.getListByPage(pageNum,pageSize,conditionVo);
        List<Depart> departLsit = departService.getDepartLsit();
        //1、存储工具类PageInfo
        modelMap.addAttribute("pageInfo",pageInfo);
        //2、数据要回显，存储查询条件的ConditionVo
        modelMap.addAttribute("conditionVo",conditionVo);
        //3、存储全部部门列表departList
        modelMap.addAttribute("departLsit",departLsit);
        return "list";
    }

    @RequestMapping(value = "/pre_add",method = RequestMethod.GET)
    public String pre_addEmp(Model model){
        List<Depart> departLsit = departService.getDepartLsit();
        model.addAttribute("departLsit",departLsit);
        return "addEmp";
    }

    /**
     * 1、springmvc框架可以自动的把前台的表单或者url中带的参数，自动封装到Employee对象
     * 2、springmvc中相应js，实际上是一个带script标签的文本
     * 3、springmvc中返回的数据类型是逻辑视图名
     * 4、如果要返回文本或者js，就需要改变方法的返回类型，应该把逻辑视图名改为文本或者js。
     *
     * 解决方案：
     *            添加注解 @ResponseBody
     *            在@RequestMapping的属性produces设置返回类型，produces = "text/html;charset=UTF-8"
     * @return
     */
    @RequestMapping(value = "/addEmp",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addEmp(Employee employee){
        if(employeeService.addEmp(employee)){
             return "<script>alert('添加成功');location.href='/list';</script>";
        }else{
            return "<script>alert('添加失败');history.go(-1);</script>";
        }
    }

    /**
     * 问题：前台页面在springmvc中传递到后台（前台到后台入参）
     * http://localhost:8080/pre_update?empid=1006
     *      pre_addEmp(@RequestParam(value = "empid")Integer empid)
     * http://localhost:8080/pre_update/1006      restful风格
     *      pre_addEmp(@PathVariable(value = "empid") Integer empid)
     * @return
     */
    @RequestMapping(value = "/pre_update/{empid}",method = RequestMethod.GET)
    public String pre_updateEmp(@PathVariable(value = "empid") Integer empid,Model model){
        Employee employee = employeeService.getEmpById(empid);
        List<Depart> departLsit = departService.getDepartLsit();
        model.addAttribute("departLsit",departLsit);
        if(null!=employee){
            model.addAttribute("employee",employee);
        }
        return "updateEmp";
    }

    @RequestMapping(value = "/updateEmp",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateEmp(Employee employee){
        if(employeeService.updateEmp(employee)){
            return "<script>alert('修改成功');location.href='/list';</script>";
        }else{
            return "<script>alert('修改失败');history.go(-1);</script>";
        }
    }

    /**
     * @RequestParam 用来接收url中？传递的参数
     * @PathVariable 用来接收 url中占位符方式传递的参数
     * @param empid
     * @return
     */
    @RequestMapping(value = "/deleteEmp",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteEmp(@RequestParam("empid") Integer empid){
        if(employeeService.deleteEmp(empid)){
            return "<script>alert('删除成功');location.href='/list';</script>";
        }else{
            return "<script>alert('删除失败');history.go(-1);</script>";
        }
    }

    /**
     * 员工详情
     * @param empid
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/viewEmp/{empid}",method = RequestMethod.GET)
    public String viewEmp(@PathVariable("empid") Integer empid,ModelMap modelMap){
        EmployeeVo employeeVo = employeeService.getEmp(empid);
        modelMap.addAttribute("employeeVo",employeeVo);
        return "detail";
    }
}
