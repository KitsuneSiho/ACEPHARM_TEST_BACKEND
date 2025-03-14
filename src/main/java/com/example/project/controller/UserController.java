package com.example.project.controller;

import com.example.project.dto.ErrorResponseDto;
import com.example.project.dto.RegisterRequestDto;
import com.example.project.dto.RegisterResponseDto;
import com.example.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //cros 설정, "/" 로 하면 전체경로
@CrossOrigin(origins = "*") //실제 배포때는 특정 도메인으로 제한 할 것
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerForm")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto requestDto) {
        try{
            RegisterResponseDto responseDto = userService.register(requestDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }catch (IllegalArgumentException e){
            // JSON 형식으로 오류 메시지 반환
            ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                    .message(e.getMessage())
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            // JSON 형태로 오류 메시지 반환
            ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                    .message("회원가입중 오류가 발생했습니다.")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

//UserController.class