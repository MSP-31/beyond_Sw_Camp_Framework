package com.beyond.university.auth.controller;

import com.beyond.university.auth.model.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AuthController {
    @Autowired
    private AuthMapper authMapper;

    @GetMapping("/login")
    public String login() {

        log.info("로그인 페이지 요청");
        System.out.println(authMapper.selectUserById("admin"));

        return "auth/login";
    }
}
