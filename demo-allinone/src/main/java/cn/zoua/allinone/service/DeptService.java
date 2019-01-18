package cn.zoua.allinone.service;

import cn.zoua.allinone.bean.Department;
import cn.zoua.allinone.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 使用redistemplate进行调用
     */
    public Department getDeptById(Integer id) {
        System.out.println("查询部门" + id);
        Department department = (Department) redisTemplate.opsForValue().get("dept:" + id);
        if (department == null) {
            department = departmentMapper.getDeptById(id);
        } else {
            return department;
        }
        //设置缓存
        redisTemplate.opsForValue().set("dept:" + id, department);
        return department;
    }


}
