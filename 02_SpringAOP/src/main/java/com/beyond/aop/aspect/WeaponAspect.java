package com.beyond.aop.aspect;

import com.beyond.aop.annotation.Repeat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class WeaponAspect {
    /*
    // * 문자를 사용하여 두가지 메소드를 적용한다.
    // @Around("execution(* com.beyond.aop.weapon.*.attack())")

    // 추상 클래스를 활용하여 두가지 메소드를 적용한다.
    // @Around("execution(* com.beyond.aop.weapon.Weapon.attack())")

    // bean의 id값과 일치하는 것만 동작시킴 / && !bean(sword) 이 경우 bow만 동작함
    // @Around("execution(* com.beyond.aop.weapon.Weapon.attack()) && !bean(sword)")

    // NoLogging 어노테이션이 붙어있어야만 설정할 수 있음
    @Around("execution(* com.beyond.aop.weapon.Weapon.attack()) &&" +
            " @annotation(com.beyond.aop.annotation.NoLogging)")
    public String attackAdvice(ProceedingJoinPoint joinPoint) {
        String result = null;

        try {
            System.out.println("공격을 준비중 입니다.");

            result = (String) joinPoint.proceed();

            System.out.println(result);
            System.out.println("공격을 성공했습니다.");
        } catch (Throwable e) {
            System.out.println("에러가 발생했습니다.");
        }
        return result;
    }
     */

    @Around("@annotation(com.beyond.aop.annotation.Repeat)")
    public String attackAdvice(ProceedingJoinPoint joinPoint) {
        String result = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // 현재 실행중인 메서드의 시그니처 반환
        Repeat repeat = signature.getMethod().getAnnotation(Repeat.class);      // Repeat 메서드 반환

        // System.out.println(repeat);
        // System.out.println(repeat.value());
        // System.out.println(repeat.count());

        try {
            System.out.println("공격을 준비중 입니다.");

            result = (String) joinPoint.proceed();

            for (int i = 0; i<repeat.count(); i++){
                System.out.println(result);
            }

            System.out.println("공격을 성공했습니다.");
        } catch (Throwable e) {
            System.out.println("에러가 발생했습니다.");
        }
        return result;
    }
}
