package com.beyond.di.config;

import com.beyond.di.pet.Cat;
import com.beyond.di.pet.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PetConfig {
    @Bean
    public Dog dog(){
        Dog dog = new Dog();

        dog.setName("코코");

        return dog;
    }

    @Bean
    @Primary // 동일한 타입의 빈이 여러개일 때 기본으로 지정
    public Cat cat(){
        return new Cat("나비");
    }
}
