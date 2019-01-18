package cn.zoua.allinone.repository;

import cn.zoua.allinone.bean.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface EmployeeRepository extends ElasticsearchRepository<Employee, Integer> {

    //参照 https://docs.spring.io/spring-data/elasticsearch/docs/3.0.6.RELEASE/reference/html/
    List<Employee> findByLastNameLike(String lastName);

}
