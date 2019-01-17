package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

    @Autowired
    RedisTemplate redisTemplate;  //k-v都是对象的

    @Autowired
    RedisTemplate empRedisTemplate;


    /**
     * Redis常见的五大数据类型
     * String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     * stringRedisTemplate.opsForValue()[String（字符串）]
     * stringRedisTemplate.opsForList()[List（列表）]
     * stringRedisTemplate.opsForSet()[Set（集合）]
     * stringRedisTemplate.opsForHash()[Hash（散列）]
     * stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    public void test01() {
        //给redis中保存数据
        stringRedisTemplate.opsForValue().append("msg", "hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }

    //测试保存对象
    @Test
    public void test02() {
        Employee empById = employeeMapper.getEmpById(2);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("emp-03", empById);
        //1、将数据以json的方式保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
//        empRedisTemplate.opsForValue().set("emp-01", empById);
        //获取到缓存中的值后，都需要强转
        Employee emp = (Employee)redisTemplate.opsForValue().get("emp-03");
        System.out.println(emp);
    }


    @Test
    public void test03() {


        // -----------------String类型数据操作 start--------------------
        ValueOperations<String, String> stringOperations = redisTemplate.opsForValue();
        // String类型数据存储，不设置过期时间，永久性保存
        stringOperations.set("string1", "fiala");
        // String类型数据存储，设置过期时间为80秒，采用TimeUnit控制时间单位
        stringOperations.set("string2", "fiala", 80, TimeUnit.SECONDS);
        // 判断key值是否存在，存在则不存储，不存在则存储
        stringOperations.setIfAbsent("string1", "my fiala");
        stringOperations.setIfAbsent("string3", "my fiala");
        String value1 = stringOperations.get("string1");
        String value2 = stringOperations.get("string3");
        System.out.println(value1);
        System.out.println(value2);
        // -----------------String类型数据操作 end--------------------

        // -----------------其他值类型数据操作 start--------------------
        Demo demo = new Demo();
        demo.setId("1");
        demo.setName("fiala");
        List<Demo> demos = new ArrayList<Demo>();
        ValueOperations<String, Object> valueOperations = redisTemplate
                .opsForValue();
        // 设置value为对象类型，且不设置过期时间，默认永久
        valueOperations.set("value1", demo);
        // 设置value为对象类型，设置过期时间为80秒，时间单位由TimeUnit控制
        valueOperations.set("value2", demos, 80, TimeUnit.SECONDS);
        Demo demo1 = (Demo) valueOperations.get("value1");
        System.out.println(demo1.toString());
        // -----------------其他值类型数据操作 end--------------------

        // -----------------List数据类型操作 start------------------
        ListOperations<String, Object> listOperations = redisTemplate
                .opsForList();
        for (int i = 0; i < 5; i++) {
            Demo listDemo = new Demo();
            listDemo.setId("\"" + i + "\"");
            listDemo.setName("fiala" + i);
            listOperations.leftPush("list1", listDemo);
            listOperations.rightPush("list2", listDemo);
        }
        // 可给数据排序
        Demo demo2 = (Demo) listOperations.leftPop("list1");
        Demo demo3 = (Demo) listOperations.rightPop("list2");
        System.out.println(demo2.toString());
        System.out.println(demo3.toString());
        // -----------------List数据类型操作 end------------------

        // -----------------set数据类型操作 start------------------
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        for (int i = 0; i < 5; i++) {
            Demo setDemo = new Demo();
            setDemo.setId("\"" + i + "\"");
            setDemo.setName("fiala" + i);
            setOperations.add("set1", setDemo);
        }
        Demo demo4 = (Demo) setOperations.pop("set1");
        System.out.println(demo4.toString());
        // -----------------set数据类型操作 end------------------

        // -----------------zset数据类型操作 start------------------
        ZSetOperations<String, Object> zSetOperations = redisTemplate
                .opsForZSet();
        zSetOperations.add("zset", "fiala", 0);
        zSetOperations.add("zset", "my fiala", 1);
        System.out.println(zSetOperations.rangeByScore("zset", 0, 1));
        // -----------------zset数据类型操作 end------------------

        // -----------------hash数据类型操作 start------------------
        HashOperations<String, Object, Object> hashOperations = redisTemplate
                .opsForHash();
        Map<String, String> map = new HashMap<String, String>();
        map.put("map1", "fiala1");
        map.put("map2", "fiala2");
        hashOperations.putAll("hash", map);
        System.out.println(hashOperations.entries("hash"));
        // -----------------hash数据类型操作 start------------------

    }


    @Test
    public void contextLoads() {

        Employee empById = employeeMapper.getEmpById(1);
        System.out.println(empById);

    }

}

class Demo {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
