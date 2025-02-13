package com.beyond.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.beyond.aop") // 컴포넌트 어노테이션이 붙은 요소들을 모두 빈으로 만들어줌
@EnableAspectJAutoProxy          // 프록시 객체 자동 생성 / AOP 사용시 필수
public class RootConfig {
}
