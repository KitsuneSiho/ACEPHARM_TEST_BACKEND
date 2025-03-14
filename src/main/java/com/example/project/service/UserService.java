package com.example.project.service;

import com.example.project.dto.RegisterRequestDto;
import com.example.project.dto.RegisterResponseDto;
import com.example.project.entity.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponseDto register(RegisterRequestDto requestDto) {
        //프론트엔드 Validation 을 우회한 접근을 차단하기 위해 2-step Validation
        // 약관 동의 확인
        if (!requestDto.isAgreeTerms() || !requestDto.isAgreePrivacy()) {
            throw new IllegalArgumentException("서비스 이용약관과 개인정보 처리방침에 동의해야 합니다.");
        }

        //비밀번호 일치 확인
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 아이디 중복 확인
        if (userRepository.existsByUserID(requestDto.getUserID())) {
            throw new IllegalArgumentException(requestDto.getUserID() + " 는 이미 사용중인 아이디입니다.");
        }

        // 사업자 번호 중복 확인
        if (userRepository.existsByBusinessNumber(requestDto.getBusinessNumber())) {
            throw new IllegalArgumentException("이미 등록된 사업자 번호입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        //사용자 엔티티 생성
        User user = User.builder()
                .userID(requestDto.getUserID())
                .password(encodedPassword)
                .representativeName(requestDto.getRepresentativeName())
                .businessNumber(requestDto.getBusinessNumber())
                .companyName(requestDto.getCompanyName())
                .memberType(requestDto.getMemberType())
                .address(requestDto.getAddress())
                .phone(requestDto.getPhone())
                .fax(requestDto.getFax())
                .email(requestDto.getEmail())
                .accountStatus(requestDto.getAccountStatus())
                .userRole(requestDto.getUserRole())
                .build();

        // 사용자 저장
        User savedUser = userRepository.save(user);

        // 응답 DTO 반환
        return RegisterResponseDto.builder()
                .id(savedUser.getId())
                .userID(savedUser.getUserID())
                .representativeName(savedUser.getRepresentativeName())
                .companyName(savedUser.getCompanyName())
                .memberType(savedUser.getMemberType())
                .accountStatus(savedUser.getAccountStatus())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }
}

//UserService.class
