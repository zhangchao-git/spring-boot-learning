package cn.zoua.allinone;

import cn.zoua.allinone.bean.Employee;
import cn.zoua.allinone.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTests {


    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void test02() {
		Employee employee = new Employee();
		employee.setId(10);
		employee.setLastName("张哈哈");
        employee.setGender(1);
        employeeRepository.index(employee);

        for (Employee emp : employeeRepository.findByLastNameLike("哈")) {
            System.out.println(emp);
        }

    }
}
