package cn.zoua.allinone.controller;

import cn.zoua.allinone.bean.Employee;
import cn.zoua.allinone.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags="员工API")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 新增员工信息
     *
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    @ApiOperation(value="用户新增")
    public Employee save(Employee employee) {
        Employee emp = employeeService.save(employee);
        return emp;
    }

    /**
     * 根据员工id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    @ApiOperation(value="查询员工")
    @ApiImplicitParam(name="id",value="员工ID",required=true)
    public Employee getEmployee(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return employee;
    }

    /**
     * 根据员工id修改员工信息
     *
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    @ApiOperation(value="员工修改")
    public Employee update(Employee employee) {
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    /**
     * 根据员工id删除员工信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delemp")
    @ApiOperation(value="查询员工")
    @ApiImplicitParam(name="id",value="员工ID",required=true)
    public String deleteEmp(Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }

    /**
     * 根据lastname查询员工
     *
     * @param lastName
     * @return
     */
    @GetMapping("/emp/lastname/{lastName}")
    @ApiOperation(value="查询员工")
    @ApiImplicitParam(name="lastName",value="员工名字",required=true)
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.getEmpByLastName(lastName);
    }

}
