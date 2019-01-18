package cn.zoua.allinone.mapper;

import cn.zoua.allinone.bean.Department;
import org.apache.ibatis.annotations.*;


/**
 * 注解版mapper
 * @Mapper 启动类统一配置后，这里就不需要配置了
 */
public interface DepartmentMapper {

    @Select("select * from department where id=#{ID}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    int updateDept(Department department);
}
