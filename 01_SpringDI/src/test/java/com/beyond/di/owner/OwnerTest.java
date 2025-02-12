package com.beyond.di.owner;

import static org.assertj.core.api.Assertions.*;

import com.beyond.di.pet.Cat;
import com.beyond.di.pet.Dog;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@DisplayName("Owner 클래스 테스트")
class OwnerTest {

    @Test
    @Disabled
    @DisplayName("실행 환경 테스트")
    void noting(){
        // 이 테스트를 통해서 현재 프로젝트가 테스트가 가능한 환경인지 확인한다.
    }

    @Test
    @DisplayName("Owner 객체 생성 테스트")
    void create(){
        // 기존에 자바 애플리케이션에서는 다형성을 통해서 객체 간의 결합도를 느슨하게 만들어준다.
        // 생성자를 통한 의존성 주입
        // Owner owner = new Owner("홍길동", 24, new Dog("멍멍이"));
        // Owner owner = new Owner("홍길동", 24, new Cat("야옹이"));

        Owner owner = new Owner();

        owner.setName("홍길동");
        owner.setAge(24);
        // 메소드를 통한 의존성 주입
        // owner.setDog(new Dog("멍멍이"));
        owner.setPet(new Dog("멍멍이"));

        assertThat(owner).isNotNull();
        assertThat(owner.getName()).isEqualTo("홍길동");
        assertThat(owner.getPet()).isNotNull();
    }

    @Test
    void genericXmlApplicationContextTest(){
        ApplicationContext context = new GenericApplicationContext();
    }
}