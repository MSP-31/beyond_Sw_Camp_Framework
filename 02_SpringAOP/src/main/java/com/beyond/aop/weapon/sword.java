package com.beyond.aop.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class sword extends Weapon {
    public sword(@Value("크리스탈 소드") String name) {
        super(name);
    }

    @Override
    public String attack() {
        return "검을 휘두른다.";
    }

    @Override
    public String toString() {
        return "sword{" +
                "name='" + name + '\'' +
                '}';
    }
}

