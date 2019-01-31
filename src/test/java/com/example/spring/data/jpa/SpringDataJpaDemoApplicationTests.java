package com.example.spring.data.jpa;

import com.example.spring.data.jpa.entity.Dept;
import com.example.spring.data.jpa.entity.User;
import com.example.spring.data.jpa.repository.DeptRepository;
import com.example.spring.data.jpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJpaDemoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Test
    // 开启事务功能是为了单元测试的时候不造成垃圾数据
//    @Transactional
    public void test() {
        Dept dept = new Dept();
        dept.setName("产品部");
        dept = deptRepository.save(dept);

        User user = new User();
        user.setName("张三2");
        user.setAge(22);
        user.setPassword("aaabbb");
        user.setDept(dept);

        userRepository.save(user);

        user = userRepository.findByName("张三1");
        System.out.println(user);
    }

    @Test
    public void testQuery() {

        User user = userRepository.findByName("李四");

        System.out.println(user);
    }

    @Test
    public void testPageQuery() {
        // 页码
        int pageNum = 0, size = 10;
        // 排序规则
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        // 分页pageable对象
        Pageable pageable = new PageRequest(pageNum, size, sort);
        Page<User> page = userRepository.findAll(pageable);
        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询的当前第" + (page.getNumber() + 1) + "页");
        System.out.println("查询的当前页面的记录数" + page.getNumberOfElements());
        System.out.println("查询的数据：" + page.getContent());
    }

    @Test
    public void testPageQuery2() {
        // 页码
        int pageNum = 0, size = 10;
        // 排序规则
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        // 分页pageable对象
        Pageable pageable = new PageRequest(pageNum, size);
//        List<User> users = userRepository.findFirst10ByName("李四", sort);
//        System.out.println(userRepository.findFirstByAge(22));

        List<User> users = userRepository.findTopByAge(22, pageable);
        System.out.println(users);

        users = userRepository.findFirst10ByAge(22, sort);
        System.out.println(users);
    }
}

