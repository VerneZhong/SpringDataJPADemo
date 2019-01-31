package com.example.spring.data.jpa.repository;

import com.example.spring.data.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 如果不想扩展Spring接口，还可以使用它来注解存储库接口@RepositoryDefinition
 * @author Mr.zxb
 * @date 2019-01-28 17:03
 */
//@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    @Override
    Page<User> findAll(Pageable pageable);

    Page<User> findByName(String name, Pageable pageable);

//    User findFirstByOrderByNameAsc(int age);

//    User findTopByOrderByAgeDesc();

    Page<User> queryFirst10ByName(String name, Pageable pageable);

    List<User> findFirst10ByAge(int age, Sort sort);

    List<User> findTopByAge(int age, Pageable pageable);

    @Modifying
    @Query("update User u set u.name = ?1 where u.id = ?2")
    int modifyByIdAndUserId(String  userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteByUserId(Long id);

    @Transactional(timeout = 10)
    @Query("select u from User u where u.age = ?1")
    User findByEmailAddress(int age);

}
