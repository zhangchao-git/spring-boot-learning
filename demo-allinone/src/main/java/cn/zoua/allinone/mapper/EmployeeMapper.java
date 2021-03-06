package cn.zoua.allinone.mapper;

import cn.zoua.allinone.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Mapper或者@MapperScan将接口扫描装配到容器中
 */
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    void insertEmp(Employee employee);

    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    void insertEmployee(Employee employee);

    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    Employee getEmpByLastName(String lastName);
}
