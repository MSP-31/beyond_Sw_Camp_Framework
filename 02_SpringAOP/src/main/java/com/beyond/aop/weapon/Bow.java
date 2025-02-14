package com.beyond.aop.weapon;

import com.beyond.aop.annotation.Repeat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("windForce")
public class Bow extends Weapon {
    public Bow(@Value("윈드포스") String name) {
        super(name);
    }

    @Override
    // 속성명이 value 일 때만 아래와 같이 사용 가능하다.
    // @Repeat("반복 횟수 지정")
    @Repeat(value = "반복횟수 3",count = 3)
    public String attack() {
        return "활을 쏜다.";
    }

    @Override
    public String toString() {
        return "Bow{" +
                "name='" + name + '\'' +
                '}';
    }
}

