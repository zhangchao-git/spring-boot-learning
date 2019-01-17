package cn.zoua.allinone.service;

import cn.zoua.allinone.bean.Employee;
import cn.zoua.allinone.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp"/*,cacheManager = "employeeCacheManager"*/) //抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(/*value = {"emp"},keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     */
    @CachePut(/*value = "emp",*/key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict：缓存清除
     */
    @CacheEvict(/*value="emp",*/beforeInvocation = true/*key = "#id",*/)
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp:" + id);
        //employeeMapper.deleteEmpById(id);
        int i = 10 / 0;
    }

    // @Caching 定义复杂的缓存规则
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
