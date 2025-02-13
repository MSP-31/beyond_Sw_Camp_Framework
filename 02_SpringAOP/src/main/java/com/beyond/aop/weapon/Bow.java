package com.beyond.aop.weapon;

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

