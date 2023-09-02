package com.github.selectshop.controller;

import com.github.selectshop.dto.SignupRequestDto;
import com.github.selectshop.dto.UserInfoDto;
import com.github.selectshop.entity.UserRoleEnum;
import javax.validation.Valid;

import com.github.selectshop.security.UserDetailsImpl;
import com.github.selectshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(
            @RequestBody SignupRequestDto requestDto
    ) {

        return ResponseEntity.ok(userService.signup(requestDto) + "<-- 얘 회원가입함");

    }

    // 회원 관련 정보 받기
    @GetMapping("/user-info")
    public ResponseEntity<UserInfoDto> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return ResponseEntity.ok(new UserInfoDto(username, isAdmin));
    }
}
