package com.example.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // IDX, user식별자

    @Column(unique = true, nullable = false)
    private String userID; // 아이디

    @Column(unique = false)
    private String password; // 비밀번호

    private String representativeName; // 대표자명

    @Column(unique = true)
    private String businessNumber; // 회사 번호

    private String companyName; // 회사이름

    private String memberType; //약국, 도매, 병원

    private String address; // 주소

    private String phone; // 연락처

    private String fax; // 팩스

    private String email; // 이메일

    @Column(nullable = false)
    private String accountStatus; // pending(대기), approved(승인), rejected(거부)

    @Column(nullable = false)
    private String userRole; //유저 권한, user, admin

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

//User.class
