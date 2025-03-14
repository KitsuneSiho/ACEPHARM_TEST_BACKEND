package com.example.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    private boolean agreeTerms;
    private boolean agreePrivacy;

    @NotBlank(message = "사용자 ID는 필수입니다")
    private String UserID;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "비밀번호는 최소 8자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다")
    private String password;

    private String passwordConfirm;

    @NotBlank(message = "대표자명은 필수입니다")
    private String representativeName;

    @NotBlank(message = "사업자 번호는 필수입니다")
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "올바른 사업자 번호 형식이 아닙니다")
    private String businessNumber;

    @NotBlank(message = "회사명은 필수입니다")
    private String companyName;

    @NotBlank(message = "회원 유형을 선택해주세요")
    private String memberType;

    @NotBlank(message = "주소는 필수입니다")
    private String address;

    @NotBlank(message = "전화번호는 필수입니다")
    private String phone;

    private String fax;

    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;

    private String accountStatus = "pending";
    private String userRole = "user";
}

//RegisterRequestDto.class