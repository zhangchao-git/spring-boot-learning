package cn.zoua.allinone.service;

import cn.zoua.allinone.bean.Employee;
import cn.zoua.allinone.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

//抽取缓存的公共配置
@CacheConfig(cacheNames = "emp"/*,cacheManager = "employeeCacheManager"*/)
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 根据id查询员工，并使用缓存
     *
     * @param id
     * @return
     */
    @Cacheable(/*value = {"emp"},keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * 更新员工信息
     *
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     */
    @CachePut(/*value = "emp",*/key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }


    /**
     * 保存员工信息
     *
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     */
    @CachePut(/*value = "emp",*/key = "#result.id")
    public Employee save(Employee employee) {
        System.out.println("updateEmp:" + employee);
        employeeMapper.insertEmp(employee);
        return employee;
    }

    /**
     * 删除员工信息
     *
     * @CacheEvict：缓存清除
     */
    @CacheEvict(/*value="emp",*/beforeInvocation = true/*key = "#id",*/)
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp:" + id);
        employeeMapper.deleteEmpById(id);
    }

    /**
     * @Caching 定义复杂的缓存规则
     */
    @Caching(
            cacheable = {
                    @Cacheable(/*value="emp",*/key = "#lastName")
            },
            put = {
                    @CachePut(/*value="emp",*/key = "#result.id"),
                    @CachePut(/*value="emp",*/key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }


}
