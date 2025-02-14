package com.beyond.aop.character;

import com.beyond.aop.weapon.Weapon;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Character {
    @Value("홍길동")
    private String name;

    @Value("78")
    private int level;

    @Autowired
    @Qualifier("windForce")
    private Weapon weapon;

    public String quest(String questName){
        // if(true){
        //     throw new RuntimeException("Quest 수행 중 예외 발생");
        // }
        // System.out.printf("%s 퀘스트를 진행중...\n",questName);
        return questName + " 퀘스트를 진행 중..";
    }
}
