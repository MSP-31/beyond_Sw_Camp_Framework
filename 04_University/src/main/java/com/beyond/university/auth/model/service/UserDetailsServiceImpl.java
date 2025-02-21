package com.beyond.university.auth.model.service;

import com.beyond.university.auth.model.mapper.AuthMapper;
import com.beyond.university.auth.model.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
    userDetailsService
        - 전달받은 정보를 통해 데이터베이스에서 일치하는 사용자를 찾아 userDetails 객체를 생성하고 반환한다.

    UserDetails
        - 스프링 시큐리티에서 사용자의 인증 정보를 담아두는 인터페이스이다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authMapper.selectUserById(username);

        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        log.info("Username : {}", user.getId());

        return user;
    }
}


















