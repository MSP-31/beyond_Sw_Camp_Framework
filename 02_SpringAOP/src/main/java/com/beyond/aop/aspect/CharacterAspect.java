package com.beyond.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/* Aspect는 먼저 Bean이 되어야함 */
@Aspect
@Component
public class CharacterAspect {
    /*
    *  Quest가 실행되기 전에 실행되어야 함.
    *  포인트 컷 지정자를 설정해줌 / * 단일 와일드 카드 문자 / .. 다중 와일드 카드 문자
    *
    *  포인트 커트 표현식
    *   - 스프링 AOP에서 포인트 커트는 AspectJ의 포인트 커트 표현식을 이용해서 정의한다.
    *   - execution([접근제한자]) 리턴타입 클래스이름.메소드이름([파라미터, ...]))
    *     - 메소드에 대한 표현식이다.
    *     - *  : 모든 값 표현한다.
    *     - .. : 0개 이상을 의미한다.
    *   - args()
    *     - 대상 메소드에 전달되는 매개값을 어드바이스에 전달하기 위한 표현식이다.
    */

    @Pointcut("execution(* com.beyond.aop.character.Character.quest(..)) && args(questName)")
    public void questPointcut(String questName) {
    }

    // @Before("execution(* com.beyond.aop.character.Character.quest(..))")
    @Before("questPointcut(questName)")
    public void beforeQuest(String questName) {
        // 퀘스트를 수행하기 전에 필요한 작업들 수행
        System.out.println(questName + " 퀘스트 준비 중...");
    }

    // @After("execution(* com.beyond.aop.character.Character.quest(..))")
    @After("questPointcut(questName)")
    public void afterQuest(String questName) {
        // 퀘스트 수행 결과에 상관없이 필요한 작업들 수행
        System.out.println(questName + " 퀘스트 수행 완료..");
    }
}
