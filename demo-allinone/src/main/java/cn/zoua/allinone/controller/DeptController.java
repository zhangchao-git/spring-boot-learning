package cn.zoua.allinone.controller;

import cn.zoua.allinone.bean.Department;
import cn.zoua.allinone.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags="部门API")
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
    @ApiOperation(value="查询部门(ID)")
    @ApiImplicitParam(name="id",value="部门ID",required=true)
    public Department getDept(@PathVariable("id") Integer id) {
        return deptService.getDeptById(id);
    }
}
