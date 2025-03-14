package com.example.project.repository;


import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //아이디 기반 유저찾기
    Optional<User> findByUserID(String userID);
    boolean existsByUserID(String userID);
    boolean existsByBusinessNumber(String businessNumber);
}

//UserRepository.interface
