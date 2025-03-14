package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {
    private Long id;
    private String userID;
    private String representativeName;
    private String companyName;
    private String memberType;
    private String accountStatus;
    private LocalDateTime createdAt;
}

//RegisterResponseDto.class
