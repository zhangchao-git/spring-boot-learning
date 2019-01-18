package cn.zoua.allinone.controller;

import cn.zoua.allinone.bean.Employee;
import cn.zoua.allinone.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.getEmpByLastName(lastName);
    }

}
