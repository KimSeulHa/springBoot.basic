package com.study.springBoot.basic.day4.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user_id")
    private List<Board> boards;

}
