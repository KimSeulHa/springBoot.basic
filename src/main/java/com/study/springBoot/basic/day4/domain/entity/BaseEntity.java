package com.study.springBoot.basic.day4.domain.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    /* 추상 클래스 abstract를 사용해서 날짜 필드를 따로 빼는 이유?
    * 다양한 테이블에서 히스토리 관리를 위해 create 날짜와 update 날짜 필드를 생성하는데
    * 개발자가 여러 명이라면 다양한 이름의 필드로 만들어질 것이다.
    * 필드명의 통일을 주기 위함이고 코드 작성을 줄이기 위함이다.
    * */

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
