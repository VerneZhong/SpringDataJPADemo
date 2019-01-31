package com.example.spring.data.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Mr.zxb
 * @date 2019-01-29 17:54
 */
@Entity
@Data
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

}
