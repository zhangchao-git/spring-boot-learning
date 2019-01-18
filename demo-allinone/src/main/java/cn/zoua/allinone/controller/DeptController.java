package cn.zoua.allinone.controller;

import cn.zoua.allinone.bean.Department;
import cn.zoua.allinone.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    /**
     * 根据部门id获取部门信息
     *
     * @param id
     * @return
     */
    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id) {
        return deptService.getDeptById(id);
    }
}
