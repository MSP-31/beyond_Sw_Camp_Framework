package com.beyond.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
    *   - bean()
    *     - 어드바이스를 적용할 빈의 ID를 지정하는 표현식이다.
    *   - @annotation()
    *     - 주어진 어노테이션을 갖는 조인 포인트에 어드바이스를 적용하기 위한 표현식이다.
    */

    @Pointcut("execution(* com.beyond.aop.character.Character.quest(..)) && args(questName)")
    public void questPointcut(String questName) {
    }

    // @Before("execution(* com.beyond.aop.character.Character.quest(..))")
    // @Before("questPointcut(questName)")
    public void beforeQuest(String questName) {
        // 퀘스트를 수행하기 전에 필요한 작업들 수행
        System.out.println(questName + " 퀘스트 준비 중...");
    }

    // @After("execution(* com.beyond.aop.character.Character.quest(..))")
    // @After("questPointcut(questName)")
    public void afterQuest(String questName) {
        // 퀘스트 수행 결과에 상관없이 필요한 작업들 수행
        System.out.println(questName + " 퀘스트 수행 완료..");
    }

    // @AfterReturning(
    //         pointcut = "execution(* com.beyond.aop.character.Character.quest(..))",
    //         returning = "result"
    // )
    public void success(String questName, String result) {
        // 퀘스트를 정상적으로 수행한 후 필요한 작업들 수행
        System.out.println("result: " + result);
        System.out.println(questName + " 퀘스트 수행 완료..");
    }

    // @AfterThrowing(
    //         pointcut = "questPointcut(questName)",
    //         throwing = "exception"
    // )
    public void fail(String questName, Exception exception) {
        // 퀘스트 수행 중 에러가 발생했을 때 필요한 작업들 수행
        System.out.println(exception.getMessage());
        System.out.println(questName + " 퀘스트 수행 중 에러가 발생했습니다..");
    }

    @Around("execution(* com.beyond.aop.character.Character.quest(..))")
    public String around(ProceedingJoinPoint joinPoint) {
        String result = null;
        // joinPoint의 첫번째 매개변수 할당
        String qeustName = (String) joinPoint.getArgs()[0];
        try {
            // before 어드바이스에 대한 기능을 수행
            System.out.println(qeustName + " 퀘스트 준비 중..");

            // 타겟 객체이 메소드를 실행시킨다.
            // joinPoint.proceed();

            // 타겟 객체의 메소드에 리턴값이 있는 경우
            // result = (String) joinPoint.proceed();

            // 타겟 객체의 메소드에 파라미터 값을 변경해서 전달하는 경우
            result = (String) joinPoint.proceed(new Object[]{qeustName});

            // after-returning 어드바이스에 대한 기능을 수행
            System.out.println("result: "+ result);
            System.out.println(qeustName + " 퀘스트 수행 완료..");

        } catch (Throwable e) {
            // after-throwing 어드바이스에 대한 기능을 수행
            System.out.println(e.getMessage());
            System.out.println(qeustName + " 퀘스트 수행중 에러가 발생");
        }
        return result;
    }
}
