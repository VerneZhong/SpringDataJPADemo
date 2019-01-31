package com.example.spring.data.jpa.repository;

import com.example.spring.data.jpa.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mr.zxb
 * @date 2019-01-30 09:19
 */
public interface DeptRepository extends JpaRepository<Dept, Long> {
}
