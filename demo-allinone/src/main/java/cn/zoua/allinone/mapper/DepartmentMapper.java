package cn.zoua.allinone.mapper;

import cn.zoua.allinone.bean.Department;
import org.apache.ibatis.annotations.*;


//注解版
//@Mapper 启动类统一配置，这里就不需要配置了
public interface DepartmentMapper {

    @Select("select * from department where id=#{ID}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
