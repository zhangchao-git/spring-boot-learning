package cn.zoua.allinone.service;

import cn.zoua.allinone.bean.Department;
import cn.zoua.allinone.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;


@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RedisCacheManager cacheManager;

    // 使用缓存管理器得到缓存，进行api调用
    public Department getDeptById(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentMapper.getDeptById(id);
        //获取某个缓存
        Cache dept = cacheManager.getCache("dept");
        dept.put("dept:1", department);
        return department;
    }


}
