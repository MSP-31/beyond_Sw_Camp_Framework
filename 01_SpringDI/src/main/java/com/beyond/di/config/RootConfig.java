package com.beyond.di.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration // 해당 클래스가 자바 설정 파일임을 선언한다.
@Import(       // 다른 클래스의 설정 파일들을 통합한다.
    value = {
        OwnerConfig.class,
        PetConfig.class
    }
)
public class RootConfig {

}
